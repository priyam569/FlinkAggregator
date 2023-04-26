package com.ezlearner.TO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import com.google.gson.Gson;

public class PersonDeserializer implements DeserializationSchema<Person> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public Person deserialize(byte[] message) throws IOException {
		String jsonString = new String(message, StandardCharsets.UTF_8);
        Gson gson = new Gson();
        Person customObject = gson.fromJson(jsonString, Person.class);
        return customObject;
	  }

    @Override
    public boolean isEndOfStream(Person nextElement) {
        // Check if the given element is the last element
        // in the stream
        return false;
    }

    @Override
    public TypeInformation<Person> getProducedType() {
        // Return the type of the deserialized object
        return TypeInformation.of(Person.class);
    }
}

