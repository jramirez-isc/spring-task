package com.leantech.task.dto;

import lombok.Data;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * DTO class for the Person entity.
 */
@Data
public class PersonDTO {

  private String name;
  private String lastName;
  private String address;
  private String cellphone;
  private String cityName;
}