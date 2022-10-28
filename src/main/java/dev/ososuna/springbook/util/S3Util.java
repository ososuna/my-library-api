package dev.ososuna.springbook.util;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class S3Util {
  
  private final AmazonS3 amazonS3;

  public S3Util(AmazonS3 amazonS3) {
    this.amazonS3 = amazonS3;
  }

  public void uploadFile(String path, String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream) {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    optionalMetaData.ifPresent(map -> {
      if (!map.isEmpty()) {
        map.forEach(objectMetadata::addUserMetadata);
      }
    });
    try {
      amazonS3.putObject(path, fileName, inputStream, objectMetadata);
    } catch (AmazonServiceException e) {
      throw new IllegalStateException("Failed to upload the file", e);
    }
  }
}
