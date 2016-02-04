package com.eigen.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CalendarSerializer extends JsonSerializer<Calendar> {

	public static final String DATE_FORMAT = "yyyyMMdd";
	
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public void serialize(Calendar calendar, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
    	
        if (calendar == null) {
            jsonGenerator.writeNull();
        } else {
            jsonGenerator.writeString(simpleDateFormat.format(calendar.getTime()));
        }
    }
    
}