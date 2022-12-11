package com.company.tourAgency.dao.impl.user;

public interface UserSqlQuery {
    // statements
    String USER_INSERT = "INSERT INTO users (email, password, name, lastname, phone)" +
            "VALUES (?, ?, ?, ?, ?)";
    String USER_FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    String USER_FIND_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    String USER_FIND_ALL = "SELECT * FROM users";
    String USER_FIND_BY_CARD_ID  = "SELECT * FROM users WHERE card_id = ?";
    String USER_AUTHENTICATE = "SELECT * FROM users WHERE email = ? AND password = ?";
    String UPDATE_USER_ROLE = "UPDATE users SET role = CAST(? AS user_role) WHERE id = ?";
    String USER_UPDATE_ADD_CARD = "UPDATE users SET card_id = ? WHERE id = ?";
    String USER_UPDATE_LOYAL = "UPDATE users SET is_loyal = ? WHERE id = ?";
    String USER_UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE id = ?";
    String USER_UPDATE_CARD_ID_TO_NULL = "UPDATE users SET card_id = NULL WHERE card_id = ?";
    String USER_DELETE = "DELETE FROM users WHERE id = ?";

    // column names
    String USER_ID_COLUMN = "id";
    String USER_PASSWORD_COLUMN = "password";
    String USER_NAME_COLUMN = "name";
    String USER_LASTNAME_COLUMN = "lastname";
    String USER_EMAIL_COLUMN = "email";
    String USER_PHONE_COLUMN = "phone";
    String USER_ROLE_COLUMN = "role";
    String USER_CARD_ID_COLUMN = "card_id";
    String USER_CREATED_AT_COLUMN = "created_at";
    String USER_IS_DELETED_COLUMN = "is_deleted";
    String USER_IS_LOYAL_COLUMN = "is_loyal";


}
