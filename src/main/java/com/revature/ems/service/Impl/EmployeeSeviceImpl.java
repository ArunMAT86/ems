package com.revature.ems.service.Impl;

import com.revature.ems.dto.EmployeeDto;
import com.revature.ems.entity.Employee;
import com.revature.ems.exception.ResourceNotFoundException;
import com.revature.ems.mapper.EmployeeMapper;
import com.revature.ems.repository.EmployeeRepository;
import com.revature.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeSeviceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeSeviceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

         Employee savedEmployee= employeeRepository.save(employee);

         return EmployeeMapper.mapToemployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee e = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("employee not found with given id" + employeeId));

        return EmployeeMapper.mapToemployeeDto(e);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> employees = employeeRepository.findAll();


       return employees.stream().map((employee -> EmployeeMapper.mapToemployeeDto(employee)))
               .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("employees not exist with given id" + employeeId)
        );

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        Employee ueb = employeeRepository.save(employee);
        return EmployeeMapper.mapToemployeeDto(ueb);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("employees not exist with given id" + employeeId)
        );

        employeeRepository.deleteById(employeeId);


    }
}
