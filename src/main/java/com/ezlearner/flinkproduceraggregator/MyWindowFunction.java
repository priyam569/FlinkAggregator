package com.ezlearner.flinkproduceraggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;

import com.ezlearner.TO.Person;

//define a custom window function to compute aggregates for each window
public class MyWindowFunction implements WindowFunction<Person, String, Integer, TimeWindow> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void apply(Integer key, TimeWindow window, Iterable<Person> personList, Collector<String> personOutputList) throws Exception{
		System.out.print("Key is===>"+key+"window time"+window.toString()+"input===>");
		List<Person> persons = new ArrayList<Person>();
		for(Person p : personList) {
			persons.add(p);
		}
		personOutputList.collect(String.format("In the window from %d to %d there were %d records that were processed for "
				+ "patient ID %d : %s",
				window.getStart(),
				window.getEnd(),
				persons.size(),
				key,
				persons.stream().map(Person::toString).collect(Collectors.joining("\n"))));
	}

	
}