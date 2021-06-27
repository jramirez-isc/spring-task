package com.leantech.task.controller;

import com.leantech.task.base.BaseTest;
import com.leantech.task.model.Employee;
import com.leantech.task.model.Person;
import com.leantech.task.model.Position;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PositionControllerTest extends BaseTest {

  @Test
  public void getAllPositionsTest() throws Exception {
    List<Position> positionList = createPositionList();
    Mockito.when(positionService.getAllPositions())
      .thenReturn(positionList);

    Position positionResult = positionList.get(0);
    Employee employeeResult = positionResult.getEmployees().get(0);
    Person personResult = employeeResult.getPerson();

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/position");
    mockMvc.perform(builder)
      .andDo(MockMvcResultHandlers.print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].name").value(positionResult.getName()))
      .andExpect(jsonPath("$[0].employees[0].salary").value(employeeResult.getSalary()))
      .andExpect(jsonPath("$[0].employees[0].person.name").value(personResult.getName()))
      .andExpect(jsonPath("$[0].employees[0].person.lastName").value(personResult.getLastName()))
      .andExpect(jsonPath("$[0].employees[0].person.address").value(personResult.getAddress()))
      .andExpect(jsonPath("$[0].employees[0].person.cellphone").value(personResult.getCellphone()))
      .andExpect(jsonPath("$[0].employees[0].person.cityName").value(personResult.getCityName()))
    ;
  }

  private List<Position> createPositionList() {
    List<Position> list = new ArrayList<>();
    Position position = new Position();
    position.setEmployees(new ArrayList<>());
    position.setName("dev");

    Employee employee = new Employee();
    employee.setSalary(BigDecimal.valueOf(2000));
    employee.setPerson(new Person());
    employee.getPerson().setName("Camilo");
    employee.getPerson().setLastName("ruiz");
    employee.getPerson().setAddress("cra");
    employee.getPerson().setCellphone("124");
    employee.getPerson().setCityName("Medellin");
    position.getEmployees().add(employee);

    list.add(position);
    return list;
  }
}