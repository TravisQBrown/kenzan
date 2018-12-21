package com.kenzan.empsvc.repositories;

import com.kenzan.empsvc.model.Employee;
import com.kenzan.empsvc.model.EmployeeStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>
{
    Optional<Employee> findById(Long id);
    Optional<Employee> findByIdAndStatus(Long id, EmployeeStatusEnum status);
}
