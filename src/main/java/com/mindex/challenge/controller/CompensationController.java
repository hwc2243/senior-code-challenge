package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.ReportingStructureService;

@RestController
public class CompensationController
{
  private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

  @Autowired
  protected CompensationService compensationService;
  
  @PostMapping("/compensation")
  public Compensation create(@RequestBody Compensation compensation) {
      LOG.debug("Received compensation create request for [{}]", compensation);

      return compensationService.create(compensation);
  }

  @GetMapping("/compensation/{id}")
  public Compensation read(@PathVariable String id) {
      LOG.debug("Received compensation request for id [{}]", id);

      return compensationService.read(id);
  }

}
