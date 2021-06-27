package com.leantech.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * Entity for Person table.
 */
@Data
@Entity(name = "PERSON")
public class Person {

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "per_id", nullable = false)
  private Integer id;
  @Basic
  @Column(name = "name")
  private String name;
  @Basic
  @Column(name = "last_name")
  private String lastName;
  @Basic
  @Column(name = "address")
  private String address;
  @Basic
  @Column(name = "cellphone")
  private String cellphone;
  @Basic
  @Column(name = "city_name")
  private String cityName;

  @ToString.Exclude
  @JsonIgnore
  @OneToOne(mappedBy = "person")
  private Employee employee;
}