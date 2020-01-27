package com.mindex.challenge.data;

import java.util.Date;

public class Compensation
{
  protected Employee employee;
  protected double salary;
  protected Date effectiveDate;
  
  public Compensation ()
  {
    
  }

  public Employee getEmployee ()
  {
    return employee;
  }

  public void setEmployee (Employee employee)
  {
    this.employee = employee;
  }

  public double getSalary ()
  {
    return salary;
  }

  public void setSalary (double salary)
  {
    this.salary = salary;
  }

  public Date getEffectiveDate ()
  {
    return effectiveDate;
  }

  public void setEffectiveDate (Date effectiveDate)
  {
    this.effectiveDate = effectiveDate;
  }
  
  
}
