package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.EmployeeDto;
import com.rhmaster.rhmaster.dtos.EmployeeRequestDto;
import com.rhmaster.rhmaster.dtos.SetEmployeeDto;
import com.rhmaster.rhmaster.dtos.UserDto;
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

    public void saveEmployee(EmployeeRequestDto employeeDto) {
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

    private EmployeeDto toEmployeeD(Employee employee) {
        UserDto user = new UserDto();
        user.setId(employee.getUser().getId());
        user.setUsername(employee.getUser().getUsername());
        user.setName(employee.getUser().getName());
        user.setLastname(employee.getUser().getLastname());
        user.setEmail(employee.getUser().getEmail());
        user.setEnabled(employee.getUser().isEnabled());

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setAdress(employee.getAdress());
        employeeDto.setCedula(employee.getCedula());
        employeeDto.setPhone(employee.getPhone());
        employeeDto.setSkills(employee.getSkills());
        employeeDto.setUnionDate(employee.getUnionDate());
        employeeDto.setLastDayDate(employee.getLastDayDate());
        employeeDto.setRetirementDate(employee.getRetirementDate());
        employeeDto.setPost(employee.getPost());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setBankEntity(employee.getBankEntity());
        employeeDto.setBankAccount(employee.getBankAccount());
        employeeDto.setWorkSection(employee.getWorkSection());
        employeeDto.setUser(user);

        return employeeDto;
    }

    public EmployeeDto getEmployeeById(UUID id) {
        Employee employee = employeeRepository.findById(id).get();
        return toEmployeeD(employee);
    }

    public void deleteEmployeeById(UUID id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDto> employeesDtos = employees.stream()
                .map(this::toEmployeeD).toList();

        return employeesDtos;
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
