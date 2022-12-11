package com.company.tourAgency.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@ToString
public class Order extends AbstractEntity {
    private int id;
    private int userId;
    private int tourId;
    private int amount;
    private Timestamp orderDate;
    private boolean isDeleted;
}
