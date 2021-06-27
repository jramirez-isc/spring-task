package com.leantech.task.dto.mapper;

import com.leantech.task.dto.EmployeeDTO;
import com.leantech.task.model.Employee;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * Mapper interface for mapping Employee to EmployeeDTO and viceversa.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDTO employeeToEmployeeDTO(Employee employee);

  Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

  List<EmployeeDTO> map(List<Employee> employees);
}
