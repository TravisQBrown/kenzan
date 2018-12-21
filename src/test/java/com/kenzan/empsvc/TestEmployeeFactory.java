package com.kenzan.empsvc;

import com.kenzan.empsvc.model.Employee;
import com.kenzan.empsvc.model.EmployeeStatusEnum;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class TestEmployeeFactory {

    public static List<Employee> GetSampleEmployees() {
        List<Employee> idList = new ArrayList<>();
            //Sample employees, using default ISO_LOCAL_DATE format...
            Employee kenzanEmp1 = new Employee("Kenzan", 'Q',
                    "EmployeeOne",
                    LocalDate.parse("1970-01-01"),
                    LocalDate.parse("2010-01-26"),
                    EmployeeStatusEnum.ACTIVE);

            idList.add(kenzanEmp1);

            Month month = Month.valueOf("April".toUpperCase());
            LocalDate doe2 = Year.now().atMonth(month).atDay(12);
            Employee kenzanEmp2 = new Employee("Kenzan", 'J',
                    "SecondEmployee",
                    LocalDate.parse("1971-10-10"),
                    doe2, EmployeeStatusEnum.ACTIVE);

            idList.add(kenzanEmp2);
            return idList;
    }
}
