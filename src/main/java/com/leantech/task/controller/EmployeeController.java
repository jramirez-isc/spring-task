package com.leantech.task.controller;

import com.leantech.task.dto.EmployeeDTO;
import com.leantech.task.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * REST Controller for Employee.
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

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
  @GetMapping
  public ResponseEntity<List<EmployeeDTO>> getEmployees(@RequestParam(name = "position", required = false) String positionName,
                                                        @RequestParam(name = "name", required = false) String personName) {
    List<EmployeeDTO> employees = employeeService.getAllByPositionAndName(positionName, personName);
    return ResponseEntity.ok().body(employees);
  }

  /**
   * Saves a new Employee object to the DB.
   * It will return the same Employee data and additionally the corresponding id from the DB.
   *
   * @param employeeReqDTO
   * @return newly created Employee Object with a valid id.
   */
  @PostMapping
  public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeReqDTO) {
    EmployeeDTO employeeResult = employeeService.createEmployee(employeeReqDTO);
    if (employeeResult == null) {
      return ResponseEntity.badRequest().body(employeeReqDTO);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(employeeResult);
  }

  /**
   * Updates an existing Employee object in the DB.
   * It will return the updated Employee object and additionally the corresponding id from the DB.
   *
   * @param id          must be included as a path parameter.
   * @param employeeReqDTO
   * @return EmployeeDTO with the updated fields, or 404 (NOT FOUND) status if the id was not found in the DB.
   */
  @PutMapping("/{id}")
  public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Integer id, @RequestBody EmployeeDTO employeeReqDTO) {
    EmployeeDTO employeeResult = employeeService.updateEmployee(id, employeeReqDTO);
    if (employeeResult == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(employeeResult);
  }

  /**
   * Deletes an existing Employee object in the DB.
   *
   * @param id must be included as a path parameter.
   * @return 200 (OK) status if the Employee was deleted successfully, or 404 (NOT FOUND) if the id was not found in the DB.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity deleteEmployee(@PathVariable("id") Integer id) {
    try {
      employeeService.deleteEmployee(id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}