package com.company.tourAgency.dao.impl.order;

public interface OrderSqlQuery {
    // statements
    String ORDER_INSERT = "INSERT INTO orders(user_id, tour_id, amount) VALUES(?, ?, ?)";
    String ORDER_FIND_BY_ID = "SELECT * FROM orders WHERE id = ?";
    String ORDER_FIND_BY_USER_ID = "SELECT * FROM orders WHERE user_id = ?";
    String ORDER_FIND_BY_TOUR_ID = "SELECT * FROM orders WHERE tour_id = ?";
    String ORDER_FIND_LAST_ADDED = "SELECT * FROM orders ORDER BY order_date";
    String ORDER_FIND_ALL = "SELECT * FROM orders ORDER BY order_date";
    String ORDER_DELETE = "DELETE FROM orders WHERE id = ?";
    String ORDER_UPDATE_USER_ID_TO_NULL = "UPDATE orders SET user_id = NULL WHERE user_id = ?";
    String ORDER_UPDATE_TOUR_ID_TO_NULL = "UPDATE orders SET tour_id = NULL WHERE tour_id = ?";


    // column names
    String ORDER_ID_COLUMN = "id";
    String ORDER_USER_ID_COLUMN = "user_id";
    String ORDER_TOUR_ID_COLUMN = "tour_id";
    String ORDER_AMOUNT_COLUMN = "amount";
    String ORDER_DATE_COLUMN = "order_date";
    String ORDER_IS_DELETED_COLUMN = "is_deleted";
}
