package com.leantech.task.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * DTO class for the Employee entity.
 */
@Data
public class EmployeeDTO {

  private int id;
  private BigDecimal salary;
  private PositionDTO position;
  private PersonDTO person;
}