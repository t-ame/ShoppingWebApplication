package com.java.util;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CcardDateValidator implements ConstraintValidator<CcardDateConstraint, String> {

	public CcardDateValidator() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
//		boolean valid = true;
		if(value == null || value == "") {
			return false;
		}
		String[] dates = value.split("/");
		if (dates.length != 2) {
			return false;
		}
		int month = 0;
		int year = 0;
		try {
			month = Integer.parseInt(dates[0]);
			year = Integer.parseInt(dates[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		int currYear = LocalDate.now().getYear()%100;
		
		if(month < 1 || month > 12 || year < currYear || year > 99) {
			return false;
		}

		return true;
	}

}
