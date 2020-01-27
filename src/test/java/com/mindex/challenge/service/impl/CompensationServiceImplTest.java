package com.mindex.challenge.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mindex.challenge.DataBootstrap;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest
{

  @Autowired
  private DataBootstrap dataBootstrap;

  @Autowired
  protected CompensationServiceImpl compensationService;
  
  @Autowired
  protected EmployeeServiceImpl employeeService;
  
  @Before
  public void init() {
      dataBootstrap.init();
  }

  @Test
  public void testCreateGet ()
  {
    double SALARY = 1000.0;
    Date effectiveDate = new Date();
    
    Employee employee = new Employee();
    employee.setFirstName("Ernie");
    employee.setLastName("Employee");
    employee = employeeService.create(employee);
    assertNotNull(employee.getEmployeeId());
    
    Compensation compensation = new Compensation();
    compensation.setEmployee(employee);
    compensation.setSalary(SALARY);
    compensation.setEffectiveDate(effectiveDate);
    compensation = compensationService.create(compensation);
    assertNotNull(compensation);
    
    Compensation persisted = compensationService.read(employee.getEmployeeId());
    assertNotNull(persisted);
    assertEquals(persisted.getEffectiveDate(), effectiveDate);
    assertTrue(persisted.getSalary() == SALARY);
  }
}
