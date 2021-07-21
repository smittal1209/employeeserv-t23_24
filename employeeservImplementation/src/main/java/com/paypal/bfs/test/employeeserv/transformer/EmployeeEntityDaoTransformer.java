package com.paypal.bfs.test.employeeserv.transformer;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

import java.util.Optional;

/**
 * Transformer class to Transform {@link Employee} to {@link EmployeeEntity} and vice versa.
 */
public class EmployeeEntityDaoTransformer {

    public static EmployeeEntity employeeEntityByEmployee(Employee employee) {
        return EmployeeEntity.builder()
                .firstName(employee.getFirstName())
                .dateOfBirth(employee.getDateOfBirth())
                .lastName(employee.getLastName())
                .build();
    }

    public static AddressEntity addressEntityByEmployee(Employee employee) {
        return AddressEntity.builder()
                .employeeId(employee.getId())
                .line1(employee.getAddress().getLine1())
                .city(employee.getAddress().getCity())
                .line2(employee.getAddress().getLine2())
                .country(employee.getAddress().getCountry())
                .zipCode(employee.getAddress().getZipCode())
                .state(employee.getAddress().getState())
                .build();
    }

    public static Employee employeeByEmployeeEntity(EmployeeEntity employeeEntity, Optional<AddressEntity> addressEntityOptional) {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        employee.setDateOfBirth(employeeEntity.getDateOfBirth());

        if (addressEntityOptional.isPresent()) {
            Address address = new Address();
            employee.setAddress(address);

            AddressEntity addressEntity = addressEntityOptional.get();
            address.setLine1(addressEntity.getLine1());
            address.setLine2(addressEntity.getLine2());
            address.setCity(addressEntity.getCity());
            address.setCountry(addressEntity.getCountry());
            address.setState(addressEntity.getState());
            address.setZipCode(addressEntity.getZipCode());

        }
        return employee;
    }
}
