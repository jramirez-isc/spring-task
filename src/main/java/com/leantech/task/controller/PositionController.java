package com.leantech.task.controller;

import com.leantech.task.model.Position;
import com.leantech.task.service.interfaces.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * REST Controller for Position.
 */
@RestController
@RequestMapping(value = "/position")
public class PositionController {

  @Autowired
  private PositionService positionService;

  /**
   * Returns a list with all Positions in the DB.
   * Each Position object in the list contains a list of Employees which is included in the response.
   * The list of Employees is returned sorted in descending order by Salary.
   * Each Employee contains a Person object which has a 1-1 relationship with Employee.
   *
   * @return List<Position> with all Positions in the DB
   */
  @GetMapping
  public ResponseEntity<List<Position>> getAllPositions() {
    List<Position> employees = positionService.getAllPositions();
    return ResponseEntity.ok().body(employees);
  }
}