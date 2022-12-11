package com.company.tourAgency.utils.validator;

import java.util.Map;

/**
 * Form Validator. Checks validity of request parameters
 */
public interface FormValidator {
    /**
     * Validate request parameters
     *
     * @param data request parameters
     * @return non-empty map if there are invalid values. map pairs:
     * key - parameter with invalid value, value - empty string.
     * empty map if there is no invalid value
     */
    Map<String, String> validate(Map<String, String[]> data);
}
