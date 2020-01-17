package com.guazi.web.utils;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class Date2LongSerializer extends JsonSerializer<Date>{

	/**
	 * Date类型的变量转换成Long类型
	 */
	
	@Override
	public void serialize(Date arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		arg1.writeNumber(arg0.getTime() / 1000);
	}

	
	
	
}
