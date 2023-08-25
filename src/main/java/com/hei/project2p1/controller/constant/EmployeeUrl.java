package com.hei.project2p1.controller.constant;

import org.springframework.stereotype.Component;

@Component
public class EmployeeUrl {
    public static final String EMPLOYEES_LIST= "/employees";
    public static final String EMPLOYEES_DETAILS= "/employees/{id}/details";
    public static final String EMPLOYEES_UPDATE= "/employees/{id}/update";
    public static final String EMPLOYEES_ADD= "/employees/add";
    public static final String EMPLOYEES_SHEET="/employees/{id}/sheet";

}
