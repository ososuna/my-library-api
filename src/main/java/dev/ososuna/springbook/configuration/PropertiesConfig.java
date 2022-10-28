package dev.ososuna.springbook.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "springbook.api")
public class PropertiesConfig {
  private String jwtSecret;
  private int jwtAccessExpirationMs;
  private int jwtRefreshExpirationMs;
  private String awsAccessKey;
  private String awsSecretKey;
  private String awsRegion;
  private String awsBucket;
}
