package com.company.tourAgency.entity;

import com.company.tourAgency.entity.enums.TourType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
public class Tour extends AbstractEntity {
    private int id;
    private String name;
    private TourType type;
    private String description;
    private Integer discount;
    private Timestamp date;
    private double price;
    private String imagePath;

}
