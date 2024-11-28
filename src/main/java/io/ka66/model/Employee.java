package io.ka66.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {

    private Integer id;
    private String name = null;
    private Double salary = null;
    private String department = null;
}
