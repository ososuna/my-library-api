package dev.ososuna.springbook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.ososuna.springbook.service.S3Service;

@RestController
@RequestMapping("/s3")
public class S3Controller {
  
  private final S3Service s3Service;

  public S3Controller(S3Service s3Service) {
    this.s3Service = s3Service;
  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
    return new ResponseEntity<>(s3Service.uploadFile(multipartFile), HttpStatus.CREATED);
  }

}
