package com.ezlearner.assigners;

import org.apache.flink.api.common.eventtime.TimestampAssigner;
import org.apache.flink.api.common.eventtime.TimestampAssignerSupplier;
import org.apache.flink.api.common.eventtime.Watermark;
import org.apache.flink.api.common.eventtime.WatermarkGenerator;
import org.apache.flink.api.common.eventtime.WatermarkOutput;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;

import com.ezlearner.TO.Person;

public class FlinkWatermarkStrategies implements WatermarkStrategy<Person> {

	@Override
	public WatermarkGenerator<Person> createWatermarkGenerator(
			org.apache.flink.api.common.eventtime.WatermarkGeneratorSupplier.Context context) {
		return new WatermarkGenerator<Person>() {

			@Override
			public void onEvent(Person event, long eventTimestamp, WatermarkOutput output) {
				output.emitWatermark(new Watermark(event.getTimespan()));
			}

			@Override
			public void onPeriodicEmit(WatermarkOutput output) {
				// DO NOTHING
			}
		};
	}

	@Override
	public TimestampAssigner<Person> createTimestampAssigner(TimestampAssignerSupplier.Context context) {

		return new TimestampAssigner<Person>() {

			@Override
			public long extractTimestamp(Person element, long recordTimestamp) { // TODO Auto-generated method stub
				return element.getTimespan();
			};
		};
	}

	// return (person, rectime)->person.getTimespan();

}
