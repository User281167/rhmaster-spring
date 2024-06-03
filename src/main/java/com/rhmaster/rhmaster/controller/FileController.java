package com.rhmaster.rhmaster.controller;


import com.rhmaster.rhmaster.Services.FileStorageService;
import com.rhmaster.rhmaster.dtos.FileDBRequestDto;
import com.rhmaster.rhmaster.dtos.FileDBResponseDto;
import com.rhmaster.rhmaster.message.ResponseFile;
import com.rhmaster.rhmaster.message.ResponseMessage;
import com.rhmaster.rhmaster.models.FileDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/archivos")
public class FileController {
    @Autowired
    private FileStorageService storageService;

    @PostMapping("/upload/{userId}/{type}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO') or hasRole('ROLE_CANDIDATO') or hasRole('ROLE_EVALUADO') or hasRole('ROLE_CONTRATADO') or hasRole('ROLE_RETIRADO')")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("userId") UUID userId, @PathVariable("type") String type) {
        String message = "";

        try {
            storageService.store(file, userId, type);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO') or hasRole('ROLE_CANDIDATO') or hasRole('ROLE_EVALUADO') or hasRole('ROLE_CONTRATADO') or hasRole('ROLE_RETIRADO')")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/metadata/{userId}/{type}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO') or hasRole('ROLE_CANDIDATO') or hasRole('ROLE_EVALUADO') or hasRole('ROLE_CONTRATADO') or hasRole('ROLE_RETIRADO')")
    public ResponseEntity<List<FileDBResponseDto>> getListFilesMetadata(@PathVariable("userId") UUID userId, @PathVariable("type") String type) {
        return storageService.getAllFilesMetadata(userId, type);
    }

    @PostMapping("/upload/metadata")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO') or hasRole('ROLE_CANDIDATO') or hasRole('ROLE_EVALUADO') or hasRole('ROLE_CONTRATADO') or hasRole('ROLE_RETIRADO')")
    public ResponseEntity<String> getListFilesMetadata(@RequestBody FileDBRequestDto fileDto) {
        return storageService.storeMetadata(fileDto);
    }


    @GetMapping("/files/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO') or hasRole('ROLE_CANDIDATO') or hasRole('ROLE_EVALUADO') or hasRole('ROLE_CONTRATADO') or hasRole('ROLE_RETIRADO')")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") UUID id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @DeleteMapping("/remove/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_PSICO') or hasRole('ROLE_CANDIDATO') or hasRole('ROLE_EVALUADO') or hasRole('ROLE_CONTRATADO') or hasRole('ROLE_RETIRADO')")
    public void deleteFile(@PathVariable("id") UUID id) {
        storageService.deleteFile(id);
    }
}
