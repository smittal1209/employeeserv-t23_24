package com.paypal.bfs.test.employeeserv.api.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

public interface EmployeeService {

    /**
     * Get an Employee by ID.
     *
     * @param id
     * @return {@link Employee}
     */
    Employee getEmployeeById(String id);

    /**
     * Creates a new Employee with the given details.
     *
     * @param employee {@link Employee}
     * @return {@link Employee}
     */
    Employee createEmployee(Employee employee);
}
