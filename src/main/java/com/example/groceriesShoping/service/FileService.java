package com.example.groceriesShoping.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String uploadImageToFileSystem(MultipartFile file) throws IOException;
    byte[] downloadImageFromFileSystem(String fileName) throws IOException;
}
