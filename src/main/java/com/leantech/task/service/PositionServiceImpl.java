package com.leantech.task.service;

import com.leantech.task.model.Employee;
import com.leantech.task.model.Position;
import com.leantech.task.repository.PositionRepository;
import com.leantech.task.service.interfaces.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * Service layer for Position.
 * Contains a method to return an existing Position or create a new one, based on an Employee request.
 * Only the getAll operation has been implemented from the CRUD operations on the Position entity.
 */
@Service
public class PositionServiceImpl implements PositionService {

  @Autowired
  private PositionRepository positionRepository;

  /**
   * Searches in the DB for an existing Position by Name.
   * If the Position exist in the DB it returns that reference.
   * If the Position doesn't exist it created a new Position and returns that reference.
   *
   * @param position object to be created.
   * @return Position object either an existing Position in the DB or a newly created one.
   */
  @Override
  public Position getByNameOrCreate(Position position) {
    if (position == null) {
      return null;
    }
    Optional<Position> positionOpt = positionRepository.findByName(position.getName());
    if (positionOpt.isPresent()) {
      return positionOpt.get();
    }
    return positionRepository.save(position);
  }

  /**
   * Returns a list with all Positions in the DB.
   * Each Position object in the list contains a list of Employees which is included in the response.
   * The list of Employees is returned sorted in descending order by Salary.
   * Each Employee contains a Person object which has a 1-1 relationship with Employee.
   *
   * @return List<Position> with all Positions in the DB
   */
  @Override
  public List<Position> getAllPositions() {
    List<Position> positions = positionRepository.findAll();
    positions.forEach(position -> Collections.sort(position.getEmployees(), Comparator.comparing(Employee::getSalary).reversed()));
    return positions;
  }
}