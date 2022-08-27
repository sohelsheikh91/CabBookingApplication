package com.springboot.cabbookingapplication.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private Integer id;
    private String name;
    private Gender gender;
    private Integer Age;
    private Location location;
}
