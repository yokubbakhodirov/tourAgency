package com.company.tourAgency.dao.impl.tour;

public interface TourSqlQuery {
    // statements
    String TOUR_INSERT = "INSERT INTO tours(name, type, description, discount, date, price, image_path) " +
            "VALUES(?, CAST(? AS tour_type), ?, ?, ?, ?, ?)";
    String TOUR_FIND_BY_ID = "SELECT * FROM tours WHERE id = ?";
    String TOUR_FIND_BY_NAME = "SELECT * FROM tours WHERE name = ?";
    String TOUR_FIND_BY_USER_ID = "SELECT t.*, o.amount FROM orders o JOIN tours t ON o.tour_id=t.id\n" +
            "WHERE o.user_id = ? ORDER BY t.date";
    String TOUR_FIND_ALL = "SELECT * FROM tours ORDER BY date";
    String TOUR_DELETE = "DELETE FROM tours WHERE id = ?";

    // column names
    String TOUR_ID_COLUMN = "id";
    String TOUR_NAME_COLUMN = "name";
    String TOUR_TYPE_COLUMN = "type";
    String TOUR_DESCRIPTION_COLUMN = "description";
    String TOUR_DISCOUNT_COLUMN = "discount";
    String TOUR_DATE_COLUMN = "date";
    String TOUR_PRICE_COLUMN = "price";
    String TOUR_IMAGE_PATH_COLUMN = "image_path";
    String ORDER_AMOUNT_COLUMN = "amount";
}
