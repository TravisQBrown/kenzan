package com.kenzan.empsvc.unit;

import com.kenzan.empsvc.TestEmployeeFactory;
import com.kenzan.empsvc.model.Employee;
import com.kenzan.empsvc.repositories.EmployeeRepository;
import com.kenzan.empsvc.services.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class EmployeeServiceTest {

    @TestConfiguration
    static class EmployeeServiceTestContextConfig {

        @Bean
        public EmployeeService employeeService() {
            return new EmployeeService();
        }
    }

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setUp() {
        long id = 1;
        List<Employee> sampleEmployees = TestEmployeeFactory.GetSampleEmployees();
        for (Iterator<Employee> i = sampleEmployees.iterator(); i.hasNext();) {
            Employee emp = i.next();
            emp.setId(id++);
        }

        sampleEmployees.stream().forEach( employee -> {
            Mockito.when(employeeRepository.findById(employee.getId()))
                    .thenReturn(Optional.of(employee));
        });

    }

    @Test
    public void whenValidId_thenEmployeeShouldBeFound() {
        try {
            Employee emp1 = employeeService.getEmployeeById(1L);

            assertThat(emp1.getId())
                    .isEqualTo(1L);
        }catch(Exception e) { Assert.fail("No employee found"); }
    }

    @Test
    public void whenRetrieveAll_thenCountShouldBeTwo() {
        assertThat(employeeService.getAllEmployees().size() == 2);
    }
}
