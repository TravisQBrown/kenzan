package com.kenzan.empsvc.unit;

import com.kenzan.empsvc.TestEmployeeFactory;
import com.kenzan.empsvc.model.Employee;
import com.kenzan.empsvc.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test the persistence layer
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void whenFindById_thenReturnEmployee() {

        List<Employee> empList = saveSampleEmployees();

        //Test GetAllEmployees
        long numEmpMatch = empList.stream()
                .mapToLong (emp -> {
                    long id = emp.getId();
                    Optional<Employee> empOpt = employeeRepository.findById(id);
                    return empOpt.isPresent() ? empOpt.get().getId() : 0L;
                })
                .filter(id -> id != 0L)
                .count();

        assertThat(empList.size() == numEmpMatch);

    }

    public List<Employee> saveSampleEmployees() {

        //List of sample employees
        List<Employee> testEmployeeList = TestEmployeeFactory.GetSampleEmployees();

        //Persist employees (will have generated IDs)
        List<Employee> persistedEmployeeList = new ArrayList<>();
        testEmployeeList.stream().forEach( employee ->
                persistedEmployeeList.add(entityManager.persist(employee))
        );

        return persistedEmployeeList;
    }
}
