package com.leantech.task.service;

import com.leantech.task.dto.EmployeeDTO;
import com.leantech.task.dto.mapper.EmployeeMapper;
import com.leantech.task.model.Employee;
import com.leantech.task.repository.EmployeeRepository;
import com.leantech.task.service.interfaces.EmployeeService;
import com.leantech.task.service.interfaces.PersonService;
import com.leantech.task.service.interfaces.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * Service layer for Employee.
 * This class contains the methods for the CRUD operations on the Employee entity.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private PositionService positionService;

  @Autowired
  private PersonService personService;

  @Autowired
  private EmployeeMapper employeeMapper;

  /**
   * Searches and return a list of Employees in the DB.
   * It applies positionName and personName filters if included.
   * If no params are send it will return all of the employees.
   * Each Employee in the list includes the corresponding Position & Person objects.
   *
   * @param positionName
   * @param personName
   * @return List<EmployeeDTO>
   */
  @Override
  public List<EmployeeDTO> getAllByPositionAndName(String positionName, String personName) {
    if (StringUtils.hasText(positionName) && StringUtils.hasText(personName)) {
      return employeeMapper.map(employeeRepository.findAllByPositionNameAndPersonName(positionName, personName));
    }
    if (StringUtils.hasText(positionName)) {
      return employeeMapper.map(employeeRepository.findAllByPositionName(positionName));
    }
    if (StringUtils.hasText(personName)) {
      return employeeMapper.map(employeeRepository.findAllByPersonName(personName));
    }
    return employeeMapper.map(employeeRepository.findAll());
  }

  /**
   * Saves a new Employee object to the DB.
   * It will return the same Employee data and additionally the corresponding id from the DB.
   *
   * @param employeeReqDTO
   * @return newly created Employee Object with a valid id.
   */
  @Transactional
  @Override
  public EmployeeDTO createEmployee(EmployeeDTO employeeReqDTO) {
    Employee employeeReq = employeeMapper.employeeDTOToEmployee(employeeReqDTO);
    if (employeeReq != null) {
      employeeReq.setPosition(positionService.getByNameOrCreate(employeeReq.getPosition()));
      return employeeMapper.employeeToEmployeeDTO(employeeRepository.save(employeeReq));
    }
    return null;
  }

  /**
   * Updates an existing Employee object in the DB.
   * It will return the updated Employee object and additionally the corresponding id from the DB.
   *
   * @param id             must be included as a path parameter.
   * @param employeeReqDTO
   * @return EmployeeDTO with the updated fields, or null if the id was not found in the DB.
   */
  @Transactional
  @Override
  public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employeeReqDTO) {
    Employee employeeReq = employeeMapper.employeeDTOToEmployee(employeeReqDTO);
    Optional<Employee> employeeDBOpt = employeeRepository.findById(id);
    if (employeeDBOpt.isPresent()) {
      Employee employeeDB = employeeDBOpt.get();
      employeeDB.setSalary(employeeReq.getSalary());
      employeeDB.setPosition(positionService.getByNameOrCreate(employeeReq.getPosition()));
      employeeDB.setPerson(personService.updatePerson(employeeDB.getPerson(), employeeReq.getPerson()));
      return employeeMapper.employeeToEmployeeDTO(employeeRepository.saveAndFlush(employeeDB));
    }
    return null;
  }

  /**
   * Deletes an existing Employee object in the DB, identified by its id.
   *
   * @param id
   */
  @Transactional
  @Override
  public void deleteEmployee(Integer id) {
    employeeRepository.deleteById(id);
  }
}