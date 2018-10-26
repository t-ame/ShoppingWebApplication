package com.java.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements
ConstraintValidator<MobileNumberConstraint, String> {
	
	@Override
    public void initialize(MobileNumberConstraint contactNumber) {
    }

	@Override
	public boolean isValid(String mobileNumber, ConstraintValidatorContext context) {
        return mobileNumber != null && mobileNumber.matches("[0-9]+")
                && (mobileNumber.length() > 8) && (mobileNumber.length() < 14);
	}

}
