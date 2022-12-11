package com.company.tourAgency.utils.validator.impl;

import com.company.tourAgency.utils.validator.FormValidator;
import com.company.tourAgency.utils.validator.PatternValidator;

import java.util.HashMap;
import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.PARAMETER_PASSWORD;
import static com.company.tourAgency.controller.navigation.AttributeParameterKey.PARAMETER_PASSWORD_REPEAT;

/**
 * Change password form validator. Checks validity of password and
 * its equality with repeated password.
 */
public class ChangePasswordFormValidator implements FormValidator {
    private static ChangePasswordFormValidator instance;
    private final PatternValidator validator;

    private ChangePasswordFormValidator() {
        validator = PatternValidator.getInstance();
    }

    public static ChangePasswordFormValidator getInstance() {
        if (instance == null) {
            instance = new ChangePasswordFormValidator();
        }
        return instance;
    }
    @Override
    public Map<String, String> validate(Map<String, String[]> data) {
        Map<String, String> errorMap = new HashMap<>();
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
        return errorMap;
    }
}
