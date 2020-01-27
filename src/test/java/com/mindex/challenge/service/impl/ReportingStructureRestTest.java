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
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureRestTest
{
  private static final String EMPLOYEE_ID = "16a596ae-edd3-4847-99fe-c4518e82c86f";
  private String reportingStructureUrl;

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private DataBootstrap dataBootstrap;

  @Before
  public void init() {
      dataBootstrap.init();
      reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
  }

  @Test
  public void testGet ()
  {
    ReportingStructure reportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, EMPLOYEE_ID).getBody();
    assertNotNull(reportingStructure);
    assertNotNull(reportingStructure.getEmployee());
    assertEquals(reportingStructure.getEmployee().getEmployeeId(), EMPLOYEE_ID);
    assertTrue(reportingStructure.getNumberOfReports() == 4);

  }
}
