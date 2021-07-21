package com.paypal.bfs.test.employeeserv.factory;

import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

import java.time.LocalDate;

public class DummyEmployeeEntityFactory {

    public static EmployeeEntity dummyEmployeeEntity() {
        return EmployeeEntity.builder()
                .id(1)
                .firstName("Employee First Name")
                .lastName("Employee Last Name")
                .dateOfBirth(LocalDate.of(1996, 2, 12))
                .build();
    }
}
