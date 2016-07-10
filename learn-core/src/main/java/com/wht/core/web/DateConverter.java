package com.wht.core.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		
		if(source == null) return null;
		
		if("".equals(source.trim())) return null;
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = null;
		
		try {
			
			date = format.parse(source);
		}catch(Exception ex) {
			
			format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = format.parse(source);
			} catch (ParseException e) {
				
			}
		}

		return date;
	}

	

}
