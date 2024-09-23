package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.EmployeeEntity;

public interface EmployeeService {

	public EmployeeEntity savedetails(EmployeeEntity employeeEntity);

	public List<EmployeeEntity> getAll();
	public EmployeeEntity getEmployee(Long empId);

	public void deleteDeatails(Long empId);

	public EmployeeEntity updateDeatails(Long empId,EmployeeEntity employeeEntity);
}