package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.FileDBRequestDto;
import com.rhmaster.rhmaster.dtos.FileDBResponseDto;
import com.rhmaster.rhmaster.models.FileDB;
import com.rhmaster.rhmaster.models.User;
import com.rhmaster.rhmaster.repository.FileDBRepository;
import com.rhmaster.rhmaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageService {
    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private UserRepository userRepository;

    private String getDefinedType(String type) {
        String definedType = type.toUpperCase();
        List<String> definedTypes = List.of(
                "NOMINA",
                "PRUEBA",
                "EXAMEN",
                "INDUCCION",
                "LIQUIDACION",
                "DOCUMENTO",
                "PRUEBA_PSICO",
                "OTRO"
        );

        if (definedTypes.contains(definedType)) {
            return definedType;
        } else {
            return "OTRO";
        }
    }

    public FileDB store(MultipartFile file, UUID userId, String type) throws IOException {
        User user = userRepository.findById(userId).get();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FileDB fileSave = FileDB.builder()
                .name(fileName)
                .fileType(file.getContentType())
                .data(file.getBytes())
                .user(user)
                .type(getDefinedType(type))
                .build();

        return fileDBRepository.save(fileSave);
    }

    public FileDB getFile(UUID id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public void deleteFile(UUID id) {
        fileDBRepository.deleteById(id);
    }

    public ResponseEntity<List<FileDBResponseDto>> getAllFilesMetadata(UUID userId, String type) {
        if (type.equalsIgnoreCase("todos")) {
            return ResponseEntity.ok(fileDBRepository.findAllByUserId(userId).stream().map(file ->
                    new FileDBResponseDto(
                            file.getId(),
                            file.getName(),
                            file.getFileType(),
                            file.getType()
                    )).toList());
        }

        List<FileDB> files = fileDBRepository.findAllByUserIdAndType(userId, type);
        List<FileDBResponseDto> filesMetadata = files.stream().map(file ->
                new FileDBResponseDto(
                        file.getId(),
                        file.getName(),
                        file.getFileType(),
                        file.getType()
                )).toList();

        return ResponseEntity.ok(filesMetadata);
    }

    public ResponseEntity<String> storeMetadata(FileDBRequestDto fileDto) {
        try {
            FileDB file = fileDBRepository.findById(fileDto.getId()).get();
            file.setName(fileDto.getName());
            file.setType(getDefinedType(fileDto.getType()));

            fileDBRepository.save(file);
            return ResponseEntity.ok("File updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("File not found");
        }
    }
}
