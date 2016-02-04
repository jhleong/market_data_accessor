package com.eigen.model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CalendarDeserializer extends JsonDeserializer<Calendar> {
	
	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(CalendarSerializer.DATE_FORMAT);
    private final Locale LOCALE = new Locale("en", "EN");
    private final TimeZone TIME_ZONE = TimeZone.getTimeZone("Asia/Singapore");

    @Override
    public Calendar deserialize(JsonParser jsonParser, DeserializationContext ctx)
            throws IOException, JsonProcessingException {
    	
        String sDate = jsonParser.getText();
        try {
            Date dtDate = simpleDateFormat.parse(sDate);
            Calendar calendar = Calendar.getInstance(TIME_ZONE, LOCALE);
            calendar.setTime(dtDate);
            return calendar;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
}