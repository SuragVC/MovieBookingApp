package com.movie.entity;

import java.nio.charset.Charset;
import java.util.Random;

public class PrimaryKeyGenerator {
	
	public static String getRandomNumberInStringFormat() {	
		Random random = new Random();
		int number = random.nextInt();
		return String.format("%06d", number);
	}
	public static Integer getRandomNumber() {	
		Random random = new Random();
		int number = random.nextInt();
		return (number);
	}
	
	public static String getRandomString() {
	    byte[] array = new byte[6]; 
	    new Random().nextBytes(array);
	    String generatedString = new String(array, Charset.forName("UTF-8"));
	    return generatedString;
	}
}
