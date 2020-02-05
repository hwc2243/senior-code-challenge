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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest
{
  @Autowired
  private DataBootstrap dataBootstrap;

  @Autowired
  protected ReportingStructureServiceImpl reportingStructureService;
  
  @Before
  public void init() {
      dataBootstrap.init();
  }

  @Test
  public void testGet ()
  {
    Throwable t = null;
    try
    {
      reportingStructureService.get("badId");
    }
    catch (Exception ex)
    {
      t = ex;
    }
    assertNotNull(t);
    
    ReportingStructure reportingStructure = reportingStructureService.get("16a596ae-edd3-4847-99fe-c4518e82c86f");
    assertNotNull(reportingStructure);
    assertTrue(reportingStructure.getNumberOfReports() == 4);
  }
}
