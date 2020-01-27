package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService
{
  @Autowired
  protected EmployeeService employeeService;
  
  @Override
  public ReportingStructure get (String employeeId)
  {
    Employee employee = employeeService.read(employeeId);
    if (employee == null)
    {
      throw new RuntimeException("Invalid employeeId: " + employeeId);
    }
    
    ReportingStructure reportingStructure = new ReportingStructure();
    reportingStructure.setEmployee(employee);
    reportingStructure.setNumberOfReports(calculateNumberOfReports(employee));
    return reportingStructure;
  }
  
  protected int calculateNumberOfReports(Employee employee)
  {
    int numberOfReports = 0;
    if (employee != null && employee.getDirectReports() != null && !employee.getDirectReports().isEmpty())
    {
      numberOfReports += employee.getDirectReports().size();
      for (Employee directReport : employee.getDirectReports())
      {
        numberOfReports += calculateNumberOfReports(employeeService.read(directReport.getEmployeeId()));
      }
    }
    return numberOfReports;
  }
}
