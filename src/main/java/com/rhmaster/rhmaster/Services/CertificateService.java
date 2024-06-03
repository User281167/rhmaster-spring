package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.CertificateDto;
import com.rhmaster.rhmaster.dtos.CertificateRequestDto;
import com.rhmaster.rhmaster.dtos.PsychoTestDto;
import com.rhmaster.rhmaster.models.Certificate;
import com.rhmaster.rhmaster.models.Employee;
import com.rhmaster.rhmaster.models.FileDB;
import com.rhmaster.rhmaster.repository.CertificateRepository;
import com.rhmaster.rhmaster.repository.EmployeeRepository;
import com.rhmaster.rhmaster.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    public ResponseEntity<String> save(CertificateRequestDto certificateDto) {
        try {
            Employee employee = employeeRepository.findById(certificateDto.getEmployeeId()).get();
            FileDB file = null;

            if (certificateDto.getFileId() != null) {
                file = fileDBRepository.findById(certificateDto.getFileId()).get();
            }

            Certificate certificate = Certificate.builder()
                    .type(certificateDto.getType())
                    .description(certificateDto.getDescription())
                    .date(certificateDto.getDate())
                    .employee(employee)
                    .file(file)
                    .isPending(true)
                    .adminResponse(certificateDto.getAdminResponse())
                    .build();

            certificateRepository.save(certificate);
            return ResponseEntity.ok("Certificate created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Certificate not created");
        }
    }

    public ResponseEntity<String> update(CertificateDto certificateDto) {
        try {
            Certificate certificate = certificateRepository.findById(certificateDto.getId()).get();
            certificate.setType(certificateDto.getType());
            certificate.setDescription(certificateDto.getDescription());
            certificate.setDate(certificateDto.getDate());
            certificate.setEmployee(employeeRepository.findById(certificateDto.getEmployeeId()).get());
            certificate.setFile(fileDBRepository.findById(certificateDto.getFileId()).get());
            certificate.setPending(certificateDto.isPending());
            certificate.setAdminResponse(certificateDto.getAdminResponse());

            certificateRepository.save(certificate);
            return ResponseEntity.ok("Certificate updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Certificate not updated");
        }
    }

    public ResponseEntity<String> delete(UUID id) {
        try {
            certificateRepository.deleteById(id);
            return ResponseEntity.ok("Certificate deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Certificate not deleted");
        }
    }

    public ResponseEntity<List<CertificateDto>> getAll() {
        try {
            List<Certificate> certificates = certificateRepository.findAll();

            List<CertificateDto> result = certificates
                    .stream()
                    .map(certificate -> {
                        UUID file = null;

                        if (certificate.getFile() != null) {
                            file = certificate.getFile().getId();
                        }

                        return CertificateDto.builder()
                            .id(certificate.getId())
                            .type(certificate.getType())
                            .description(certificate.getDescription())
                            .date(certificate.getDate())
                            .date(certificate.getDate())
                            .employeeId(certificate.getEmployee().getId())
                            .fileId(file)
                            .pending(certificate.isPending())
                            .adminResponse(certificate.getAdminResponse())
                            .build();
                    }).toList();

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<List<CertificateDto>> findByEmployeeId(UUID employeeId) {
        try {
            List<Certificate> certificates = certificateRepository.findByEmployeeId(employeeId);

            List<CertificateDto> result = certificates
                    .stream()
                    .map(certificate -> CertificateDto.builder()
                            .id(certificate.getId())
                            .type(certificate.getType())
                            .description(certificate.getDescription())
                            .date(certificate.getDate())
                            .date(certificate.getDate())
                            .employeeId(certificate.getEmployee().getId())
                            .fileId(certificate.getFile().getId())
                            .pending(certificate.isPending())
                            .adminResponse(certificate.getAdminResponse())
                            .build())
                    .toList();

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
