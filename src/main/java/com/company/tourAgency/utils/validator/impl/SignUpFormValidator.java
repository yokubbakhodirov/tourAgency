package com.company.tourAgency.utils.validator.impl;

import com.company.tourAgency.utils.validator.FormValidator;
import com.company.tourAgency.utils.validator.PatternValidator;

import java.util.HashMap;
import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;

/**
 * Sign up form validator. Checks validity of user fields.
 */
public class SignUpFormValidator implements FormValidator {
    private static SignUpFormValidator instance;
    private static PatternValidator validator;

    private SignUpFormValidator() {
        validator = PatternValidator.getInstance();
    }

    public static SignUpFormValidator getInstance() {
        if (instance == null) {
            instance = new SignUpFormValidator();
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
        String password = "";
        if (data.get(PARAMETER_PASSWORD) == null || data.get(PARAMETER_PASSWORD).length == 0
                || !validator.isPasswordValid(data.get(PARAMETER_PASSWORD)[0])) {
            errorMap.put(PARAMETER_PASSWORD, "");
        } else {
            password = data.get(PARAMETER_PASSWORD)[0];
        }

        if (!password.isBlank() && !password.equals(data.get(PARAMETER_PASSWORD_REPEAT)[0])) {
            errorMap.put(PARAMETER_PASSWORD_REPEAT, "");
        }
        if (data.get(PARAMETER_NAME) == null || data.get(PARAMETER_NAME).length == 0
                || !validator.isNameValid(data.get(PARAMETER_NAME)[0])) {
            errorMap.put(PARAMETER_NAME, "");
        }
        if ((data.get(PARAMETER_LASTNAME) != null && data.get(PARAMETER_LASTNAME).length == 0) ||
        (data.get(PARAMETER_LASTNAME) != null && !validator.isLastnameValid(data.get(PARAMETER_LASTNAME)[0]))) {
            errorMap.put(PARAMETER_LASTNAME, "");
        }
        if (data.get(PARAMETER_PHONE) == null || data.get(PARAMETER_PHONE).length == 0
                || !validator.isPhoneValid(data.get(PARAMETER_PHONE)[0])) {
            System.out.println();
            errorMap.put(PARAMETER_PHONE, "");
        }
        return errorMap;
    }
}
