package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Interface for employee resource operations.
 */
@RequestMapping("/v1/bfs/employees")
public interface EmployeeResource {

    /**
     * Gets the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") @Valid @NotNull String id);

    /**
     * Creates the {@link Employee} resource.
     *
     * @param employee {@link Employee}.
     * @return {@link Employee} resource.
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee);
}
