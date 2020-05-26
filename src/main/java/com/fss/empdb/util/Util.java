package com.fss.empdb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

	private Util() {
		
	}
	
	public static Date convertStringToDate(final String str) throws ParseException {		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd");		
		return sf.parse(str);		
	}
	
}
