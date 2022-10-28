package dev.ososuna.springbook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AWSConfig {
  
  private final PropertiesConfig propertiesConfig;

  public AWSConfig(PropertiesConfig propertiesConfig) {
    this.propertiesConfig = propertiesConfig;
  }

  @Bean
  public AmazonS3 s3() {
    AWSCredentials awsCredentials = new BasicAWSCredentials(propertiesConfig.getAwsAccessKey(), propertiesConfig.getAwsSecretKey());
    return AmazonS3ClientBuilder
      .standard()
      .withRegion(propertiesConfig.getAwsRegion())
      .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
      .build();
  }
}
