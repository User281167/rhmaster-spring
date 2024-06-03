package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.PsychoTestRequestDto;
import com.rhmaster.rhmaster.dtos.PsychoTestDto;
import com.rhmaster.rhmaster.models.FileDB;
import com.rhmaster.rhmaster.models.PsychoTest;
import com.rhmaster.rhmaster.repository.FileDBRepository;
import com.rhmaster.rhmaster.repository.PsychoTestRepository;
import com.rhmaster.rhmaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PsychoTestService {
    @Autowired
    private PsychoTestRepository psychoTestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    public ResponseEntity<String> save(PsychoTestRequestDto psychoTestDto) {
        try {
            FileDB file = null;

            if (psychoTestDto.getFileId() != null) {
                file = fileDBRepository.findById(psychoTestDto.getFileId()).get();
            }

            PsychoTest test = PsychoTest.builder()
                    .notes(psychoTestDto.getNotes())
                    .date(psychoTestDto.getDate())
                    .user(userRepository.findById(psychoTestDto.getUserId()).get())
                    .file(file)
                    .build();

            psychoTestRepository.save(test);
            return ResponseEntity.ok("Test created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Test not created");
        }
    }

    public ResponseEntity<String> update(PsychoTestDto psychoTestDto) {
        try {
            PsychoTest test = psychoTestRepository.findById(psychoTestDto.getId()).get();
            test.setNotes(psychoTestDto.getNotes());
            test.setDate(psychoTestDto.getDate());
            test.setUser(userRepository.findById(psychoTestDto.getUserId()).get());
            test.setFile(fileDBRepository.findById(psychoTestDto.getFileId()).get());

            psychoTestRepository.save(test);
            return ResponseEntity.ok("Test updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Test not updated");
        }
    }

    public ResponseEntity<String> delete(UUID id) {
        try {
            psychoTestRepository.deleteById(id);
            return ResponseEntity.ok("Test deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Test not deleted");
        }
    }

    // findall tests
    public ResponseEntity<List<PsychoTestDto>> getAll() {
        try {
            List<PsychoTest> tests = psychoTestRepository.findAll();

            List<PsychoTestDto> dtos = tests.stream().map(test -> {
                UUID fileId = null;

                if (test.getFile() != null) {
                    fileId = test.getFile().getId();
                }

                PsychoTestDto dto = new PsychoTestDto();
                dto.setId(test.getId());
                dto.setNotes(test.getNotes());
                dto.setDate(test.getDate());
                dto.setUserId(test.getUser().getId());
                dto.setFileId(fileId);
                return dto;
            }).toList();

            return ResponseEntity.ok(dtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
