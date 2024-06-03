package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.AssignmentDto;
import com.rhmaster.rhmaster.dtos.EmployeeAssignmentRequestDto;
import com.rhmaster.rhmaster.dtos.EmployeeAssignmentResponseDto;
import com.rhmaster.rhmaster.models.*;
import com.rhmaster.rhmaster.repository.AssignmentRepository;
import com.rhmaster.rhmaster.repository.EmployeeAssignmentRepository;
import com.rhmaster.rhmaster.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeAssignmentService {
    @Autowired
    private EmployeeAssignmentRepository employeeAssignmentRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity<String>  save(EmployeeAssignmentRequestDto requestDto) {
        try {
            Assignment assignment = assignmentRepository.findById(requestDto.getAssignmentId()).get();
            Employee employee = employeeRepository.findById(requestDto.getEmployeeId()).get();
            EmployeeAssignment employeeAssignment;

            if (requestDto.getId() == null) {
                employeeAssignment = EmployeeAssignment.builder()
                        .notes(requestDto.getNotes())
                        .employee(employee)
                        .assignment(assignment)
                        .build();
            } else {
                employeeAssignment = employeeAssignmentRepository.findById(requestDto.getId()).get();
                employeeAssignment.setNotes(requestDto.getNotes());
                employeeAssignment.setAssignment(assignment);
                employeeAssignment.setEmployee(employee);
            }

            employeeAssignmentRepository.save(employeeAssignment);
            return ResponseEntity.ok("Guardado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar");
        }
    }

    public List<EmployeeAssignmentResponseDto> getAllByEmployeeId(UUID assignmentId, UUID employeeId) {
        List<EmployeeAssignment> assignments = employeeAssignmentRepository.findAllByEmployeeId(employeeId);

        Assignment notes = assignmentRepository.findById(assignmentId).get();

        return assignments.stream().map(b -> {
            EmployeeAssignmentResponseDto responseDto = new EmployeeAssignmentResponseDto();
            responseDto.setId(b.getId());
            responseDto.setEmployeeId(employeeId);
            responseDto.setAssignment(new AssignmentDto(notes));
            return responseDto;
        }).toList();
    }

    public ResponseEntity<String> deleteById(UUID id) {
        try {
            employeeAssignmentRepository.deleteById(id);
            return ResponseEntity.ok("Eliminado con exito");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar");
        }
    }

    public List<EmployeeAssignmentResponseDto> getAll() {
        List<EmployeeAssignment> all = employeeAssignmentRepository.findAll();

        return all.stream().map(b -> {
            EmployeeAssignmentResponseDto responseDto = new EmployeeAssignmentResponseDto();
            responseDto.setId(b.getId());
            responseDto.setEmployeeId(b.getEmployee().getId());
            responseDto.setAssignment(new AssignmentDto(b));
            return responseDto;
        }).toList();
    }
}
