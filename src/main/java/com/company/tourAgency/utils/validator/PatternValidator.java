package com.company.tourAgency.utils.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern Validator. Checks validity of values based on its patterns.
 */
public final class PatternValidator {
    private static PatternValidator instance;

    public static final int TEXT_LENGTH = 50;
    public static final String EMAIL_PATTERN =
            "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    public static final String PASSWORD_PATTERN =
            "^[\\p{Alnum}\\p{Punct}]{4,20}$";
    public static final String PHONE_PATTERN = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";

    public static final String CARD_PATTERN = "[0-9]{12}";
    public static final String ONLY_NUMBER_PATTERN = "[0-9]+";
    public static final String ONLY_TEXT_PATTERN = "^[a-zA-Z]{3,20}$";

    private PatternValidator() {
    }

    public static PatternValidator getInstance() {
        if (instance == null) {
            instance = new PatternValidator();
        }
        return instance;
    }

    public boolean isPasswordValid(String password) {
        return password != null &&
                password.matches(PASSWORD_PATTERN) &&
                password.length() < TEXT_LENGTH;

    }

    public boolean isNameValid(String name) {
        return name != null &&
                name.matches(ONLY_TEXT_PATTERN);
    }

    public boolean isLastnameValid(String lastname) {
        return lastname != null &&
                lastname.matches(ONLY_TEXT_PATTERN);
    }

    public boolean isPhoneValid(String phone) {
        if (phone == null) return false;
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }


    public boolean isEmailValid(String email) {
        return email != null &&
                email.matches(EMAIL_PATTERN) &&
                email.length() < TEXT_LENGTH;
    }

    public boolean isCardValid(String cardNumber) {
        return cardNumber != null &&
                cardNumber.matches(CARD_PATTERN);
    }

    public boolean isAmountValid(String amount) {
        return amount != null &&
                amount.matches(ONLY_NUMBER_PATTERN) &&
                amount.length() <= 2 &&
                Integer.parseInt(amount) >= 1;
    }

    public boolean isDescriptionValid(String description) {
        return description != null &&
                description.length() < 1000 &&
                description.length() >= 4;
    }

    public boolean isTourTypeValid(String tourType) {
        boolean result = switch (tourType.toUpperCase()) {
            case "REST", "SHOPPING", "EXCURSION" -> true;
            default -> false;
        };

        return result;
    }

    public boolean isDiscountValid(String discount) {
        if (discount == null) return false;
        try {
            int discountInt = Integer.parseInt(discount);
            return discountInt >= 0 && discountInt < 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isTimestampValid(String timeStamp) {
        if (timeStamp == null) return false;
        SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            format.parse(timeStamp);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean isPriceValid(String price) {
        if (price == null) return false;
        try {
            double priceDouble = Double.parseDouble(price);
            return priceDouble >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isImagePathValid(String imagePath) {
        if (imagePath == null) return false;
        return imagePath.length() <= 1000 && imagePath.startsWith("https://");
    }
}
