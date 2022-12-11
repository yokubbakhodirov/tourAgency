package com.company.tourAgency.utils.validator.impl;

import com.company.tourAgency.utils.validator.FormValidator;
import com.company.tourAgency.utils.validator.PatternValidator;

import java.util.HashMap;
import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;
import static com.company.tourAgency.controller.navigation.AttributeParameterKey.PARAMETER_EMAIL;

/**
 * Sign in form validator. Checks validity of email and password.
 */
public class SignInFormValidator implements FormValidator {
    private static SignInFormValidator instance;
    private static PatternValidator validator;

    private SignInFormValidator() {
        validator = PatternValidator.getInstance();
    }

    public static SignInFormValidator getInstance() {
        if (instance == null) {
            instance = new SignInFormValidator();
        }
        return instance;
    }
    @Override
    public Map<String, String> validate(Map<String, String[]> data) {
        Map<String, String> errorMap = new HashMap<>();
        if (data.get(PARAMETER_EMAIL) == null || data.get(PARAMETER_EMAIL).length == 0
                || !validator.isEmailValid(data.get(PARAMETER_EMAIL)[0])) {
            errorMap.put(PARAMETER_EMAIL, "");
        }
        if (data.get(PARAMETER_PASSWORD) == null || data.get(PARAMETER_PASSWORD).length == 0
                || !validator.isPasswordValid(data.get(PARAMETER_PASSWORD)[0])) {
            errorMap.put(PARAMETER_PASSWORD, "");
        }
        return errorMap;
    }
}
