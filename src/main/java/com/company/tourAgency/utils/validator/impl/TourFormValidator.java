package com.company.tourAgency.utils.validator.impl;

import com.company.tourAgency.utils.validator.FormValidator;
import com.company.tourAgency.utils.validator.PatternValidator;

import java.util.HashMap;
import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;

/**
 * Tour form validator. Checks validity of tour fields.
 */
public class TourFormValidator implements FormValidator {
    private static TourFormValidator instance;
    private static PatternValidator validator;

    private TourFormValidator() {
        validator = PatternValidator.getInstance();
    }

    public static TourFormValidator getInstance() {
        if (instance == null) {
            instance = new TourFormValidator();
        }
        return instance;
    }

    @Override
    public Map<String, String> validate(Map<String, String[]> data) {
        Map<String, String> errorMap = new HashMap<>();
        if (data.get(PARAMETER_NAME) == null || data.get(PARAMETER_NAME).length == 0
                || !validator.isNameValid(data.get(PARAMETER_NAME)[0])) {
            errorMap.put(PARAMETER_NAME, "");
        }
        if (data.get(PARAMETER_TYPE) == null || data.get(PARAMETER_TYPE).length == 0
                || !validator.isTourTypeValid(data.get(PARAMETER_TYPE)[0])) {
            errorMap.put(PARAMETER_TYPE, "");
        }
        if (data.get(PARAMETER_DESCRIPTION) == null || data.get(PARAMETER_DESCRIPTION).length == 0
                || !validator.isDescriptionValid(data.get(PARAMETER_DESCRIPTION)[0])) {
            errorMap.put(PARAMETER_DESCRIPTION, "");
        }
        if (data.get(PARAMETER_DISCOUNT) == null || data.get(PARAMETER_DISCOUNT).length == 0
                || !validator.isDiscountValid(data.get(PARAMETER_DISCOUNT)[0])) {
            errorMap.put(PARAMETER_DISCOUNT, "");
        }

        if (data.get(PARAMETER_DATE) == null || data.get(PARAMETER_DATE).length == 0) {
            errorMap.put(PARAMETER_DATE, "");
        } else {
            String timestamp = data.get(PARAMETER_DATE)[0];
            timestamp = timestamp.replace("T", " ");
            System.out.println("timestamp = " + timestamp);
            if (!validator.isTimestampValid(timestamp)) {
                errorMap.put(PARAMETER_DATE, "");
            }
        }

        if (data.get(PARAMETER_PRICE) == null || data.get(PARAMETER_PRICE).length == 0
                || !validator.isPriceValid(data.get(PARAMETER_PRICE)[0])) {
            errorMap.put(PARAMETER_PRICE, "");
        }
        if (data.get(PARAMETER_IMAGE_PATH) == null || data.get(PARAMETER_IMAGE_PATH).length == 0
                || !validator.isImagePathValid(data.get(PARAMETER_IMAGE_PATH)[0])) {
            errorMap.put(PARAMETER_IMAGE_PATH, "");
        }

        return errorMap;
    }

}
