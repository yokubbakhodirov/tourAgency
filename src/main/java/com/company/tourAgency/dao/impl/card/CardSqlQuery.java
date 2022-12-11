package com.company.tourAgency.dao.impl.card;

public interface CardSqlQuery {
    // statements
    String CARD_INSERT = "INSERT INTO card(number) VALUES(?)";
    String CARD_FIND_BY_ID = "SELECT * FROM card WHERE id = ?";
    String CARD_FIND_BY_CARD_NUMBER = "SELECT * FROM card WHERE number = ?";
    String CARD_DELETE = "DELETE FROM card WHERE id = ?";

    // column names
    String CARD_COLUMN_ID = "id";
    String CARD_COLUMN_CARD_NUMBER = "number";
}
