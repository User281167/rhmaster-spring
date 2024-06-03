package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.AssignmentDto;
import com.rhmaster.rhmaster.dtos.AssignmentRequestDto;
import com.rhmaster.rhmaster.models.Assignment;
import com.rhmaster.rhmaster.models.FileDB;
import com.rhmaster.rhmaster.repository.AssignmentRepository;
import com.rhmaster.rhmaster.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AssignmentService {
    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    FileDBRepository fileDBRepository;

    private String getDefinedType(String type) {
        type = type.toUpperCase();

        List<String> types = List.of(
                "EXAMEN",
                "PRUEBA",
                "INDUCCION",
                "CAPACITACION",
                "RETIRO",
                "OTRO"
        );

        if (types.contains(type)) {
            return type;
        } else {
            return "OTRO";
        }
    }

    public ResponseEntity<String> save(AssignmentRequestDto requestDto) {
        try {
            FileDB file = null;

            if (requestDto.getFileId() != null) {
                file = fileDBRepository.findById(requestDto.getFileId()).get();
            }

            Assignment assignment = Assignment.builder()
                    .title(requestDto.getTitle())
                    .type(getDefinedType(requestDto.getType()))
                    .description(requestDto.getDescription())
                    .date(requestDto.getDate())
                    .file(file)
                    .build();

            assignmentRepository.save(assignment);
            return ResponseEntity.ok("Assignment created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Assignment not created");
        }
    }

    public ResponseEntity<String> update(AssignmentDto assignmentDto) {
        try {
            Assignment assignment = assignmentRepository.findById(assignmentDto.getId()).get();
            assignment.setType(getDefinedType(assignmentDto.getType()));
            assignment.setTitle(assignmentDto.getTitle());
            assignment.setDescription(assignmentDto.getDescription());
            assignment.setDate(assignmentDto.getDate());
            assignmentRepository.save(assignment);

            return ResponseEntity.ok("Assignment updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Assignment not updated");
        }
    }

    public ResponseEntity<String> delete(UUID id) {
        try {
            assignmentRepository.deleteById(id);
            return ResponseEntity.ok("Assignment deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Assignment not deleted");
        }
    }

    public ResponseEntity<List<AssignmentDto>> getAll() {
        try {
            List<Assignment> assignments = assignmentRepository.findAll();

            List<AssignmentDto> result = assignments
                    .stream()
                    .map(assignment -> {
                        UUID file = null;

                        if (assignment.getFile() != null) {
                            file = assignment.getFile().getId();
                        }

                        return AssignmentDto.builder()
                                .id(assignment.getId())
                                .title(assignment.getTitle())
                                .type(getDefinedType(assignment.getType()))
                                .description(assignment.getDescription())
                                .date(assignment.getDate())
                                .fileId(file)
                                .build();
                    }).toList();

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<List<AssignmentDto>> findType(String type) {
        try {
            List<Assignment> assignments = assignmentRepository.findAllByType(type);

            List<AssignmentDto> result = assignments
                    .stream()
                    .map(assignment -> {
                        FileDB file = assignment.getFile();
                        UUID fileId = null;

                        if (file != null) {
                            fileId = file.getId();
                        }

                        return AssignmentDto.builder()
                                .id(assignment.getId())
                                .title(assignment.getTitle())
                                .type(getDefinedType(assignment.getType()))
                                .description(assignment.getDescription())
                                .date(assignment.getDate())
                                .fileId(fileId)
                                .build();
                    }).toList();

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
