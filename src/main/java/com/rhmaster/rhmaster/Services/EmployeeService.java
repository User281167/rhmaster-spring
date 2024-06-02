package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.EmployeeDto;
import com.rhmaster.rhmaster.dtos.SetEmployeeDto;
import com.rhmaster.rhmaster.models.Employee;
import com.rhmaster.rhmaster.models.WorkSection;
import com.rhmaster.rhmaster.repository.EmployeeRepository;
import com.rhmaster.rhmaster.repository.WorkSectionRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    WorkSectionRepositoy workSectionRepositoy;

    public void saveEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId()).get();
        employee.setAdress(employeeDto.getAdress());
        employee.setCedula(employeeDto.getCedula());
        employee.setPhone(employeeDto.getPhone());
        employee.setSkills(employeeDto.getSkills());
        employee.setUnionDate(employeeDto.getUnionDate());
        employee.setLastDayDate(employeeDto.getLastDayDate());
        employee.setRetirementDate(employeeDto.getRetirementDate());
        employee.setPost(employeeDto.getPost());
        employee.setSalary(employeeDto.getSalary());
        employee.setBankEntity(employeeDto.getBankEntity());
        employee.setBankAccount(employeeDto.getBankAccount());

        WorkSection section = workSectionRepositoy.findById(employeeDto.getWorkSection()).get();
        employee.setWorkSection(section);

        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(UUID id) {
        return employeeRepository.findById(id).get();
    }

    public void deleteEmployeeById(UUID id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public ResponseEntity<String> update(SetEmployeeDto employeeDto) {
        try {
            Employee employee = employeeRepository.findById(employeeDto.getEmployeeId()).get();
            employee.setAdress(employeeDto.getAddress());
            employee.setPhone(employeeDto.getPhone());
            employee.setSkills(employeeDto.getSkills());

            employeeRepository.save(employee);
            return ResponseEntity.ok("Employee updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating employee");
        }
    }
}
