package com.leantech.task.controller;

import com.leantech.task.base.BaseTest;
import com.leantech.task.dto.EmployeeDTO;
import com.leantech.task.dto.PersonDTO;
import com.leantech.task.dto.PositionDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmployeeControllerTest extends BaseTest {

  @Test
  public void getEmployeesTest() throws Exception {
    List<EmployeeDTO> employeeDTOList = createEmployeeRestDTOList();
    Mockito.when(employeeService.getAllByPositionAndName(Mockito.any(String.class), Mockito.any(String.class)))
      .thenReturn(employeeDTOList);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/employee?" +
                                                                         "position=" + "position" +
                                                                         "&name=" + "name");
    mockMvc.perform(builder)
      .andDo(MockMvcResultHandlers.print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].salary").value(employeeDTOList.get(0).getSalary()))
      .andExpect(jsonPath("$[0].position.name").value(employeeDTOList.get(0).getPosition().getName()))
      .andExpect(jsonPath("$[0].person.name").value(employeeDTOList.get(0).getPerson().getName()))
      .andExpect(jsonPath("$[0].person.lastName").value(employeeDTOList.get(0).getPerson().getLastName()))
      .andExpect(jsonPath("$[0].person.address").value(employeeDTOList.get(0).getPerson().getAddress()))
      .andExpect(jsonPath("$[0].person.cellphone").value(employeeDTOList.get(0).getPerson().getCellphone()))
      .andExpect(jsonPath("$[0].person.cityName").value(employeeDTOList.get(0).getPerson().getCityName()))
    ;
  }

  @Test
  public void createEmployeeTest() throws Exception {
    EmployeeDTO employeeDTO = createEmployeeRestDTO();
    Mockito.when(employeeService.createEmployee(Mockito.any(EmployeeDTO.class)))
      .thenReturn(employeeDTO);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/employee")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON)
      .characterEncoding("UTF-8")
      .content(this.mapper.writeValueAsBytes(new EmployeeDTO()));

    mockMvc.perform(builder)
      .andExpect(status().isCreated());
  }

  @Test
  public void updateEmployeeTest() throws Exception {
    EmployeeDTO employeeDTO = createEmployeeRestDTO();
    Mockito.when(employeeService.updateEmployee(Mockito.any(Integer.class), Mockito.any(EmployeeDTO.class)))
      .thenReturn(employeeDTO);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/employee/1")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON)
      .characterEncoding("UTF-8")
      .content(this.mapper.writeValueAsBytes(new EmployeeDTO()));

    mockMvc.perform(builder)
      .andExpect(status().isOk());
  }

  @Test
  public void deleteEmployeeTest() throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/employee/2")
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON)
      .characterEncoding("UTF-8");

    mockMvc.perform(builder)
      .andExpect(status().isOk());
  }

  private List<EmployeeDTO> createEmployeeRestDTOList() {
    List<EmployeeDTO> list = new ArrayList<>();
    EmployeeDTO employeeDTO = createEmployeeRestDTO();
    list.add(employeeDTO);
    return list;
  }

  private EmployeeDTO createEmployeeRestDTO() {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    employeeDTO.setPosition(new PositionDTO());
    employeeDTO.setPerson(new PersonDTO());
    employeeDTO.setSalary(BigDecimal.valueOf(2000));
    employeeDTO.getPosition().setName("dev");
    employeeDTO.getPerson().setName("Camilo");
    employeeDTO.getPerson().setLastName("ruiz");
    employeeDTO.getPerson().setAddress("cra");
    employeeDTO.getPerson().setCellphone("124");
    employeeDTO.getPerson().setCityName("Medellin");
    return employeeDTO;
  }
}