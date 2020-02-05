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
public class CompensationRestTest
{
  private String compensationUrl;
  private String compensationIdUrl;
  private String employeeUrl;

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Before
  public void init() {
    compensationUrl = "http://localhost:" + port + "/compensation";
    compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    employeeUrl = "http://localhost:" + port + "/employee";
  }

  @Test
  public void testGet ()
  {
    double SALARY = 1000.0;
    Date EFFECTIVE_DATE = new Date();

    Employee testEmployee = new Employee();
    testEmployee.setFirstName("Ernie");
    testEmployee.setLastName("Employee");

    Employee persistedEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

    Compensation testCompensation = new Compensation();
    testCompensation.setEmployee(persistedEmployee);
    testCompensation.setSalary(SALARY);
    testCompensation.setEffectiveDate(EFFECTIVE_DATE);
    
    Compensation persistedCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
    assertNotNull(persistedCompensation);
    assertEquals(persistedCompensation.getEffectiveDate(), EFFECTIVE_DATE);
    assertTrue(persistedCompensation.getSalary() == SALARY);
    
    Compensation retrievedCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, persistedEmployee.getEmployeeId()).getBody();
    assertNotNull(retrievedCompensation);
    assertEquals(testEmployee.getFirstName(), retrievedCompensation.getEmployee().getFirstName());
    assertEquals(testEmployee.getLastName(), retrievedCompensation.getEmployee().getLastName());
    assertEquals(retrievedCompensation.getEffectiveDate(), EFFECTIVE_DATE);
    assertTrue(retrievedCompensation.getSalary() == SALARY);
  }
}
