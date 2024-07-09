package com.example.groceriesShoping.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class FolderConfig {

    @Value("${folder.path}")
    private String folderPath;

}
