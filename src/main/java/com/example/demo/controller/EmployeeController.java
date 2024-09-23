package com.example.demo.controller;


import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.EmployeeResponseDTO;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeEntity employeeEntity;
	
	@PostMapping("/postEmployeeDetails")
	public EmployeeEntity saveMethod(  @RequestParam("employee") String  employeeEntityJson,  @RequestParam("file") MultipartFile file) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
	    EmployeeEntity employeeEntity = objectMapper.readValue(employeeEntityJson, EmployeeEntity.class);
		employeeEntity.setData(file.getBytes());
		return employeeService.savedetails(employeeEntity);
	}
	
	  @GetMapping("/employee/Image/{id}")
	    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
	    	EmployeeEntity emp = employeeService.getEmployee(id);
	        if (emp != null) {
	            return ResponseEntity.ok()
	                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + emp.getName() + "\"")
	                    .contentType(MediaType.IMAGE_JPEG) // Adjust according to your image type
	                    .body(emp.getData());
	        }
	        return ResponseEntity.notFound().build();
	    }
	    

	    @GetMapping("/employee/{id}")
	    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable Long id) {
	        EmployeeEntity emp = employeeService.getEmployee(id);
	        
	        if (emp != null) {
	            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
	            responseDTO.setEmpId(emp.getEmpId());
	            responseDTO.setEmpName(emp.getEmpName());
	            responseDTO.setEmpSpouse(emp.getEmpSpouse());
	            responseDTO.setEmpFatherName(emp.getEmpFatherName());
	            responseDTO.setEmpMotherName(emp.getEmpMotherName());
	            responseDTO.setEmpPhoneNumber(emp.getEmpPhoneNumber());
	            responseDTO.setGender(emp.getGender());
	            responseDTO.setAddress(emp.getAddress());
	            responseDTO.setName(emp.getName());

	            // Convert image bytes to Base64 string
	            if (emp.getData() != null) {
	                responseDTO.setImage(Base64.getEncoder().encodeToString(emp.getData()));
	            }

	            return ResponseEntity.ok(responseDTO);
	        }

	        return ResponseEntity.notFound().build();
	    }
	  
	  
	
	@GetMapping("/getEmployeeDetails")
	public List<EmployeeEntity> getDetails() {
		return employeeService.getAll();
	}
	
	@PutMapping("/updateEmployeeDetails/{empId}")
	public EmployeeEntity update(@PathVariable Long empId,@RequestBody EmployeeEntity employeeEntity) {
		return employeeService.updateDeatails(empId,employeeEntity);
	}
	
	@DeleteMapping("/deleteEmployeeDetails/{empId}")
	public void delete(@PathVariable Long empId) {
		  employeeService.deleteDeatails(empId);
	}
	
}
