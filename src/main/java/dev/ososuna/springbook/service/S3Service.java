package dev.ososuna.springbook.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.ososuna.springbook.configuration.PropertiesConfig;
import dev.ososuna.springbook.util.S3Util;

@Service
public class S3Service {

  private final PropertiesConfig propertiesConfig;
  private final S3Util s3Util;

  public S3Service(S3Util s3Util, PropertiesConfig propertiesConfig) {
    this.s3Util = s3Util;
    this.propertiesConfig = propertiesConfig;
  }

  public Map<String, String> uploadFile(MultipartFile file) {
    
    if (file.isEmpty()) {
      throw new IllegalStateException("Cannot upload empty file");
    }
    
    Map<String, String> metadata = new HashMap<>();
    metadata.put("Content-Type", file.getContentType());
    metadata.put("Content-Length", String.valueOf(file.getSize()));
    
    UUID folderName = UUID.randomUUID();
    String path = String.format("%s/%s", propertiesConfig.getAwsBucket(), folderName);
    String fileName = String.format("%s", file.getOriginalFilename());
    
    try {
      s3Util.uploadFile(path, fileName, Optional.of(metadata), file.getInputStream());
    } catch (IOException e) {
      throw new IllegalStateException("Failed to upload file", e);
    }

    Map<String, String> fileUploaded = new HashMap<>();
    fileUploaded.put("message", String.format("File %s uploaded successfully", fileName));
    fileUploaded.put("path", path);
    fileUploaded.put("url", String.format("%s/%s/%s", propertiesConfig.getS3BaseUrl(), folderName.toString(), fileName));
    fileUploaded.put("fileName", fileName);
    return fileUploaded;
  }
}
