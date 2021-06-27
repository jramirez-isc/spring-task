package com.leantech.task.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leantech.task.TaskApplication;
import com.leantech.task.service.interfaces.EmployeeService;
import com.leantech.task.service.interfaces.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {TaskApplication.class})
@AutoConfigureMockMvc
@Transactional
public abstract class BaseTest {

  @Autowired
  public ObjectMapper mapper;

  @Autowired
  public MockMvc mockMvc;

  @MockBean
  public EmployeeService employeeService;

  @MockBean
  public PositionService positionService;

}