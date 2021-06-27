package com.leantech.task.service.interfaces;

import com.leantech.task.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

  List<EmployeeDTO> getAllByPositionAndName(String positionName, String personName);

  EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

  EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeDTO);

  void deleteEmployee(Integer id);
}
