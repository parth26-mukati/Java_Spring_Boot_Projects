package com.example.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.EmployeeEntity;
import com.example.service.EmployeeService;
import com.lowagie.text.DocumentException;

import PdfExporter.ExcelExporter;
import PdfExporter.PdfExporter;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/pdf")
	 public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
       response.setContentType("application/pdf");
       DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
       String currentDateTime = dateFormatter.format(new Date());

       String headerKey = "Content-Disposition";
       String headerValue = "attachment; filename=Employee_" + currentDateTime + ".pdf";
       response.setHeader(headerKey, headerValue);

       List<EmployeeEntity> listUsers = employeeService.getAllEmployees();

       PdfExporter exporter = new PdfExporter(listUsers);
       exporter.export(response);

   }

	// export to excelsheet
	 @GetMapping("/excel")
	    public void exportToExcel(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());

	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=Employee_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);

	        List<EmployeeEntity> listUsers = employeeService.getAllEmployees();

	        ExcelExporter excelExporter = new ExcelExporter(listUsers);

	        excelExporter.export(response);    
	    }  
	
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	@GetMapping("/test")
	public String test() {
		return "Server is up";
	}
	
	@GetMapping("/employees")
	public List<EmployeeEntity> getAllEmployee(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public EmployeeEntity getEmployee(@PathVariable String id){
		return employeeService.getEmployees(id);
	}
	
	//1. Done
	@PutMapping("/updateempsalary/{id}/{salary}")
	public void updateSalary(@PathVariable String id,@PathVariable String salary) {
		employeeService.updateEmployeeSalary(id,salary);
	}
	
	//2. Done
	@PutMapping("/updateempdesg/{id}/{designation}")
	public void updateDesignation(@PathVariable String id,@PathVariable String designation) {
		employeeService.updateEmployeeDesignation(id,designation);
	}
	
	//3. Done
	@PostMapping("/addemployee")
	public void addEmployee(@RequestBody EmployeeEntity e) {
		employeeService.addEmployees(e);
	}
	
	//4. Done
	@DeleteMapping("/deleteemployee/{id}")
	public void deleteEmployee(@PathVariable String id) {
		employeeService.deleteEmployees(id);
	}
	
	//5. Done
	@PutMapping("/updateempaddress/{id}/{address}")
	public void updateAddress(@PathVariable String id,@PathVariable String address) {
		employeeService.updateEmployeeAddress(id,address);
	}
}
