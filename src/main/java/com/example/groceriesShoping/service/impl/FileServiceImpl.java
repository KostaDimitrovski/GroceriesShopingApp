package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.config.FolderConfig;
import com.example.groceriesShoping.model.FileData;
import com.example.groceriesShoping.repository.FileDataRepository;
import com.example.groceriesShoping.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    private final FileDataRepository fileDataRepository;
    private final String FOLDER_PATH;
    public FileServiceImpl(FileDataRepository fileDataRepository, FolderConfig folderConfig) {
        this.fileDataRepository = fileDataRepository;
        this.FOLDER_PATH = folderConfig.getFolderPath();
    }


    @Override
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath=FOLDER_PATH+file.getOriginalFilename();

        FileData fileData=fileDataRepository.save(FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build());

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return filePath;
        }
        return null;
    }

    @Override
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }
}
