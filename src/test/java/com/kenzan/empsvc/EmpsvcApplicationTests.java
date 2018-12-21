package com.kenzan.empsvc;

import com.kenzan.empsvc.model.Employee;
import com.kenzan.empsvc.repositories.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Iterator;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestPropertySource( locations = "classpath:integrationtest.properties")
public class EmpsvcApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getEmployeeOne_returnsStatus200()
            throws Exception {

        List<Employee> empList = createSampleEmployees();
        Long empOneId = empList.get(0).getId();
        mvc.perform(get("/api/employee/"+ empOneId)
                .header("Authorization","kenzan"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateEmployeeOne_returnsStatus200()
            throws Exception {

        List<Employee> empList = createSampleEmployees();
        empList.get(0).setLastName("NewLastName");
        String foo = empList.get(0).toString();
        System.out.println(foo);
        mvc.perform(MockMvcRequestBuilders
                .put("/api/employee/")
                .content(empList.get(0).toString())
                .header("Authorization","kenzan")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("NewLastName"));
    }

    public List<Employee> createSampleEmployees() {
        List<Employee> sampleEmployees = TestEmployeeFactory.GetSampleEmployees();

        for (Iterator<Employee> itr = sampleEmployees.iterator(); itr.hasNext();) {
            Employee emp = itr.next();
            employeeRepository.save(emp);
        }
        return employeeRepository.findAll();
    }
}

