package com.revature.ems.mapper;

import com.revature.ems.dto.EmployeeDto;
import com.revature.ems.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToemployeeDto(Employee employee){

        return new EmployeeDto(employee.getId(),employee.getFirstName(),employee.getLastName(),employee.getEmail());
    }

    public static Employee mapToEmployee(EmployeeDto employeedto){

        return  new Employee(employeedto.getId(),employeedto.getFirstName(),employeedto.getLastName(),employeedto.getEmail());
    }


}
