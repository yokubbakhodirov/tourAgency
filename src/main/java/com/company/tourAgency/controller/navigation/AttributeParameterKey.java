package com.company.tourAgency.controller.navigation;

/**
 * Attribute parameter key. Contains session and request attributes and parameters.
 */
public class AttributeParameterKey {
    // Session attribute keys
    public static final String SESSION_ATTRIBUTE_USER = "user";
    public static final String SESSION_ATTRIBUTE_CURRENT_PAGE = "current_page";
    public static final String SESSION_ATTRIBUTE_LOCALIZATION = "localization";
    public static final String SESSION_ATTRIBUTE_EMAIL = "email";
    public static final String SESSION_ATTRIBUTE_KEY_SENT = "key_sent";
    public static final String SESSION_ATTRIBUTE_KEY_RECEIVED = "key_received";
    public static final String SESSION_ATTRIBUTE_CARD_NUMBER = "card_number";


    // Request attribute and parameter keys
    public static final String REQUEST_PARAMETER_COMMAND = "command";
    public static final String REQUEST_PARAMETER_LOCALIZATION = "localization";
    public static final String REQUEST_ATTRIBUTE_USER_EXISTS = "user_exists";
    public static final String REQUEST_ATTRIBUTE_TOURS = "tours_list";
    public static final String REQUEST_ATTRIBUTE_MY_TOURS = "my_tours_list";
    public static final String REQUEST_ATTRIBUTE_CARD_EXISTS = "card_exists";
    public static final String REQUEST_ATTRIBUTE_PURCHASE_SUCCESS = "purchase_success";
    public static final String REQUEST_ATTRIBUTE_ADD_TOUR_SUCCESS = "add_tour_success";
    public static final String REQUEST_ATTRIBUTE_TOUR_EXISTS = "tour_exists";
    public static final String REQUEST_ATTRIBUTE_CONFIRM_KEY_INVALID = "confirm_key_invalid";
    public static final String REQUEST_ATTRIBUTE_PARAMETER_USERS = "users";
    public static final String REQUEST_ATTRIBUTE_PARAMETER_ORDERS = "orders";
    public static final String REQUEST_ATTRIBUTE_ADMIN_DATA = "admin_data";


    // validation
    public static final String  REQUEST_ATTRIBUTE_FORM_INVALID = "form_invalid";
    public static final String  REQUEST_ATTRIBUTE_USER_INVALID = "user_invalid";


    // user parameters
    public static final String PARAMETER_ID = "id";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_PASSWORD_REPEAT = "password_repeat";
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_LASTNAME = "lastname";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_USER_ROLE = "role";
    public static final String PARAMETER_CARD_ID = "card_id";
    public static final String PARAMETER_IS_LOYAL = "is_loyal";
    public static final String PARAMETER_IS_DELETED = "is_deleted";
    public static final String PARAMETER_CREATED_AT = "created_at";


    // tour parameters
    public static final String PARAMETER_PHONE = "phone";
    public static final String PARAMETER_TYPE = "type";
    public static final String PARAMETER_DESCRIPTION = "description";
    public static final String PARAMETER_DISCOUNT = "discount";
    public static final String PARAMETER_DATE = "date";
    public static final String PARAMETER_PRICE = "price";
    public static final String PARAMETER_IMAGE_PATH = "image_path";

    // order parameters
    public static final String PARAMETER_USER_ID = "user_id";
    public static final String PARAMETER_TOUR_ID =  "tour_id";
    public static final String PARAMETER_AMOUNT = "amount";
    public static final String PARAMETER_ORDER_DATE = "order_date";


    // card parameters
    public static final String PARAMETER_CARD_NUMBER = "card_number";
    public static final String PARAMETER_ATTRIBUTE_TOUR_AMOUNT = "tour_amount";
    public static final String PARAMETER_ATTRIBUTE_TOUR_ID = "tour_id";


    // change password
    public static final String PARAMETER_RECEIVED_KEY = "received_key";


}
