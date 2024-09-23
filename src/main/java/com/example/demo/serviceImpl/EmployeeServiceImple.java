package com.example.demo.serviceImpl;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImple implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeEntity savedetails(EmployeeEntity employeeEntity) {
		return employeeRepository.save(employeeEntity);
	}

	public List<EmployeeEntity> getAll() {
		return employeeRepository.findAll();
	}

	@Override
	public void deleteDeatails(Long empId) {
		// TODO Auto-generated method stub
		 employeeRepository.deleteById(empId);
	}

	@Override
	public EmployeeEntity updateDeatails(Long empId,EmployeeEntity employeeEntity){
		Optional<EmployeeEntity> existingEmployee = employeeRepository.findById(empId);
        if (existingEmployee.isPresent()) {
            // Update the existing employee details
            EmployeeEntity employeeToUpdate = existingEmployee.get();
            employeeToUpdate.setEmpName(employeeEntity.getEmpName()); // Add appropriate setters
            employeeToUpdate.setEmpSpouse(employeeEntity.getEmpSpouse());
            employeeToUpdate.setEmpFatherName(employeeEntity.getEmpFatherName());
            employeeToUpdate.setEmpMotherName(employeeEntity.getEmpMotherName());
            employeeToUpdate.setEmpPhoneNumber(employeeEntity.getEmpPhoneNumber());
            employeeToUpdate.setGender(employeeEntity.getGender());
            employeeToUpdate.setAddress(employeeEntity.getAddress());
            
            // Save the updated employee entity
            return employeeRepository.save(employeeToUpdate);
        } else {
            throw new RuntimeException("Employee not found with ID: " + empId);  // Handle error appropriately
        }
    }

	@Override
	public EmployeeEntity getEmployee(Long empId) {
		// TODO Auto-generated method stub
		return employeeRepository.getById(empId);
	}
	}


