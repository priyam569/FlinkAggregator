package com.ezlearner.flinkproduceraggregator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collector;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.SlidingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ezlearner.TO.CustomProperties;
import com.ezlearner.TO.Person;
import com.ezlearner.TO.PersonDeserializer;
import com.ezlearner.assigners.FlinkWatermarkStrategies;

@SpringBootApplication
public class FlinkProducerAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlinkProducerAggregatorApplication.class, args);
		try {
			callFlinkConsumer("persontopic");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static FlinkKafkaConsumer<Person> createPersonConsumerforPersonTopic(String topic, String kafkaAddress) {
		CustomProperties props = new CustomProperties();
		props.setProperty("bootstrap.servers", "localhost:9092");// props.getBootstrapAddress());
		props.setProperty("group.id", "consumer2");
		props.setProperty("flink.version", "1.13");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("value.deserializer", "class org.apache.kafka.common.serialization.ByteArrayDeserializer");

		FlinkKafkaConsumer<Person> p = new FlinkKafkaConsumer<Person>("persontopic", new PersonDeserializer(), props);
		return p;

	}

	public static void callFlinkConsumer(String inputTopic) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		// env.getConfig().addDefaultOption("--add-opens",
		// "java.base/java.lang=ALL-UNNAMED");
		CustomProperties props = new CustomProperties();
		FlinkKafkaConsumer<Person> person = createPersonConsumerforPersonTopic(inputTopic, "localhost:9092");// props.getBootstrapAddress());
		DataStream<Person> stream = env.addSource(person).assignTimestampsAndWatermarks(new FlinkWatermarkStrategies());
		KeyedStream<Person, Integer> keyedStream = stream.keyBy(po -> po.getId());
		
		/*
		 * WindowedStream<Person, Integer, TimeWindow> windowedstream = keyedStream
		 * .window(SlidingEventTimeWindows.of(Time.minutes(10), Time.minutes(1)));
		 */
		 
		WindowedStream<Person, Integer, TimeWindow> windowedstream = keyedStream
				.window(TumblingEventTimeWindows.of(Time.minutes(1)));
		DataStream<String> timedPersonData = windowedstream.apply(new MyWindowFunction());
		timedPersonData.print();
		env.execute("Kafka flink Example");
	}
}
