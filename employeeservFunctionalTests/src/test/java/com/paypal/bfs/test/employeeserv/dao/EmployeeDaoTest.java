package com.paypal.bfs.test.employeeserv.dao;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.impl.EmployeeDaoImpl;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.factory.DummyEmployeeEntityFactory;
import com.paypal.bfs.test.employeeserv.repository.AddressRepository;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeDaoTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private EmployeeDaoImpl employeeDao;

    @Test
    public void testGetEmployeeByIdSuccess() {
        EmployeeEntity dummyEmployee = DummyEmployeeEntityFactory.dummyEmployeeEntity();

        when(employeeRepository.findById(1))
                .thenReturn(Optional.of(dummyEmployee));

        Employee actual = employeeDao.getEmployeeById(1);

        Assert.assertEquals(dummyEmployee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(dummyEmployee.getLastName(), actual.getLastName());
        Assert.assertEquals(dummyEmployee.getDateOfBirth(), actual.getDateOfBirth());
        Assert.assertEquals(dummyEmployee.getId(), actual.getId());
    }

    @Test
    public void testGetEmployeeByIdNotFound() {
        when(employeeRepository.findById(1))
                .thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = catchThrowableOfType(() -> employeeDao.getEmployeeById(1), EntityNotFoundException.class);

        Assert.assertEquals("Employee with id: 1 not found.", entityNotFoundException.getMessage());
    }
}
