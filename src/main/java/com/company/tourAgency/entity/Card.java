package com.company.tourAgency.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Card extends AbstractEntity {
    private int id;
    private String cardNumber;
    private double balance;
}
