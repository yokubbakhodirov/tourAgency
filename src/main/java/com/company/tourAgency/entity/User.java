package com.company.tourAgency.entity;

import com.company.tourAgency.entity.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
@Builder
public class User extends AbstractEntity {
    private Integer id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private UserRole role;
    private Integer cardId;
    private boolean isLoyal;
    private Date createdAt;
    private boolean isDeleted;
}
