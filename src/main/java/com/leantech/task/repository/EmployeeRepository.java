package com.leantech.task.repository;

import com.leantech.task.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

  List<Employee> findAllByPositionNameAndPersonName(String positionName, String personName);

  List<Employee> findAllByPositionName(String positionName);

  List<Employee> findAllByPersonName(String personName);
}
