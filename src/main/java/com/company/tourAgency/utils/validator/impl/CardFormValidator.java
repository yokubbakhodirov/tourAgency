package com.company.tourAgency.utils.validator.impl;

import com.company.tourAgency.utils.validator.FormValidator;
import com.company.tourAgency.utils.validator.PatternValidator;

import java.util.HashMap;
import java.util.Map;

import static com.company.tourAgency.controller.navigation.AttributeParameterKey.*;

/**
 * Card form validator. Checks validity of card number and tour amount.
 */
public class CardFormValidator implements FormValidator {
    private static CardFormValidator instance;
    private static PatternValidator validator;

    private CardFormValidator() {
        validator = PatternValidator.getInstance();
    }

    public static CardFormValidator getInstance() {
        if (instance == null) {
            instance = new CardFormValidator();
        }
        return instance;
    }

    @Override
    public Map<String, String> validate(Map<String, String[]> data) {
        return null;
    }

    public Map<String, String> validate(Object cardNumber, Object tourAmount) {
        Map<String, String> errorMap = new HashMap<>();
        if (cardNumber == null || ((String) cardNumber).length() == 0
                || !validator.isCardValid((String) cardNumber)) {
            errorMap.put(PARAMETER_CARD_NUMBER, "");
        }
        if (tourAmount == null || ((String) tourAmount).length() == 0
                || !validator.isAmountValid((String) tourAmount)) {
            errorMap.put(PARAMETER_ATTRIBUTE_TOUR_AMOUNT, "");
        }
        return errorMap;
    }
}
