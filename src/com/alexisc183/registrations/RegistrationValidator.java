package com.alexisc183.registrations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidator {
	public enum Validation {
		EMPTY_NAME,
		EMPTY_IP,
		BAD_IP_FORMAT,
		IP_WITH_A_VALUE_EXCEEDING_255,
		OK
	}
	
	private String name, ip;
	
	public RegistrationValidator(String name, String ip) {
		this.name = name;
		this.ip = ip;
	}
	
	public Validation validate() {
		if (name.length() == 0) {
			return Validation.EMPTY_NAME;
		}
		if (ip.length() == 0) {
			return Validation.EMPTY_IP;
		}
		
		Pattern pattern = Pattern.compile("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$");
		Matcher matcher = pattern.matcher(ip);
		
		if (!matcher.find()) {
			return Validation.BAD_IP_FORMAT;
		}
		
		for (String ipValue : ip.split("\\.")) {
			if (Short.parseShort(ipValue) > 255) {
				return Validation.IP_WITH_A_VALUE_EXCEEDING_255;
			}
		}
		
		return Validation.OK;
	}
}
