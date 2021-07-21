package com.paypal.bfs.test.employeeserv.dao.impl;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDao;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.AddressRepository;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.transformer.EmployeeEntityDaoTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
@Slf4j
public class EmployeeDaoImpl implements EmployeeDao {
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDaoImpl(AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(id);

        if (!optionalEmployeeEntity.isPresent()) {
            log.error("Employee not found id : {}", id);
            throw new EntityNotFoundException("Employee with id: " + id + " not found.");
        }
        EmployeeEntity employeeEntity = optionalEmployeeEntity.get();

        Optional<AddressEntity> byEmployeeId = addressRepository.findByEmployeeId(employeeEntity.getId());

        return EmployeeEntityDaoTransformer.employeeByEmployeeEntity(employeeEntity, byEmployeeId);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = EmployeeEntityDaoTransformer.employeeEntityByEmployee(employee);
        employeeEntity = employeeRepository.save(employeeEntity);

        employee.setId(employeeEntity.getId());

        if (employee.getAddress() != null) {
            log.info("Adding Address for employee with id {}", employee.getId());
            AddressEntity addressEntity = EmployeeEntityDaoTransformer.addressEntityByEmployee(employee);
            addressRepository.save(addressEntity);
        }
        return employee;
    }
}
