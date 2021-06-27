package com.leantech.task.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author jramirez-isc
 * created on 06/25/21
 * <p>
 * Entity for Position table.
 */
@Data
@Entity(name = "POSITION")
public class Position {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "pos_id", nullable = false)
  private Integer id;
  @Basic
  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
  private List<Employee> employees;
}