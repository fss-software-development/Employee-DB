package com.fss.empdb.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@ToString
public class EmployeeSearchCriteria {

    Long employeeSqId;

    Long employeeId;

    String employeeName;

    private Department[] department;

    private Account[] account;

    private Region[] region;

    private Location[] location;

    private Grade[] grade;

    private Designation[] designation;

    private BillableStatus[] billableStatus;

    private ServiceLine[] serviceLine;

    private Academics[] academics;




    public Long getEmployeeSqId() {
        return employeeSqId;
    }

    public void setEmployeeSqId(Long employeeSqId) {
        this.employeeSqId = employeeSqId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Department[] getDepartment() {
        return department;
    }

    public void setDepartment(Department[] department) {
        this.department = department;
    }

    public Account[] getAccount() {
        return account;
    }

    public void setAccount(Account[] account) {
        this.account = account;
    }

    public Region[] getRegion() {
        return region;
    }

    public void setRegion(Region[] region) {
        this.region = region;
    }

    public Location[] getLocation() {
        return location;
    }

    public void setLocation(Location[] location) {
        this.location = location;
    }

    public Grade[] getGrade() {
        return grade;
    }

    public void setGrade(Grade[] grade) {
        this.grade = grade;
    }

    public Designation[] getDesignation() {
        return designation;
    }

    public void setDesignation(Designation[] designation) {
        this.designation = designation;
    }

    public BillableStatus[] getBillableStatus() {
        return billableStatus;
    }

    public void setBillableStatus(BillableStatus[] billableStatus) {
        this.billableStatus = billableStatus;
    }

    public ServiceLine[] getServiceLine() {
        return serviceLine;
    }

    public void setServiceLine(ServiceLine[] serviceLine) {
        this.serviceLine = serviceLine;
    }

    public Academics[] getAcademics() {
        return academics;
    }

    public void setAcademics(Academics[] academics) {
        this.academics = academics;
    }

}



