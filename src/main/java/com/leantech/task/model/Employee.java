package com.leantech.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * Entity for Employee table.
 */
@Data
@Entity(name = "EMPLOYEE")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "emp_id", nullable = false)
  private Integer id;

  @Basic
  @Column(name = "salary")
  private BigDecimal salary;

  @ToString.Exclude
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "pos_id")
  private Position position;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "per_id")
  private Person person;
}