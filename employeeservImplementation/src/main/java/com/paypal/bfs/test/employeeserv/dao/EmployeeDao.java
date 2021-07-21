package com.paypal.bfs.test.employeeserv.dao;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

import javax.persistence.EntityNotFoundException;

public interface EmployeeDao {

    /**
     * Get and return the transformed employee record.
     *
     * @param id
     * @return {@link Employee}
     * @throws EntityNotFoundException
     */
    Employee getEmployeeById(Integer id) throws EntityNotFoundException;

    /**
     * Transform the employee request and saves to database.
     *
     * @param employee {@link Employee}
     * @return {@link Employee}
     */
    Employee createEmployee(Employee employee);
}
