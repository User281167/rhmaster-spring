package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.models.Employee;
import com.rhmaster.rhmaster.models.FileDB;
import com.rhmaster.rhmaster.models.User;
import com.rhmaster.rhmaster.repository.EmployeeRepository;
import com.rhmaster.rhmaster.repository.FileDBRepository;
import com.rhmaster.rhmaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileStorageService {
    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private UserRepository userRepository;

    public FileDB store(MultipartFile file, UUID userId) throws IOException {
        User user = userRepository.findById(userId).get();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FileDB fileSave = FileDB.builder()
                .name(fileName)
                .fileType(file.getContentType())
                .data(file.getBytes())
                .user(user)
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
}
