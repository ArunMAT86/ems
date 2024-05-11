package com.revature.ems.controller;


import com.revature.ems.dto.EmployeeDto;
import com.revature.ems.entity.Employee;
import com.revature.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }


    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable ("id") Long employeeId){

        EmployeeDto empolyeedto = employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(empolyeedto);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getallEmployees(){

        List<EmployeeDto> e = employeeService.getAllEmployees();
        return ResponseEntity.ok(e);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmpolyee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto employeeDto){

        EmployeeDto e = employeeService.updateEmployee(employeeId,employeeDto);

        return ResponseEntity.ok(e);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmpoyee(@PathVariable("id") Long employeeId){

        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok("employee deleted succesfully.");
    }


}
