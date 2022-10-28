package dev.ososuna.springbook.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ososuna.springbook.configuration.PropertiesConfig;
import dev.ososuna.springbook.model.RegisterRequest;
import dev.ososuna.springbook.model.User;
import dev.ososuna.springbook.service.AuthService;
import dev.ososuna.springbook.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
  
  private static final String AUTHORIZATION = "authorization";
  private static final String APPLICATION_JSON_VALUE = "application/json";
  private final AuthService authService;
  private final UserService userService;
  private final PropertiesConfig propertiesConfig;

  public AuthController(AuthService authService, UserService userService, PropertiesConfig propertiesConfig) {
    this.authService = authService;
    this.userService = userService;
    this.propertiesConfig = propertiesConfig;
  }

  @PostMapping(value="/register")
  public ResponseEntity<User> register(@RequestBody RegisterRequest request) throws ResponseStatusException {
    return new ResponseEntity<>(authService.register(request), HttpStatus.OK);
  }

  @GetMapping(value="/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String refresh_token = authorizationHeader.substring("Bearer ".length());
      if (refresh_token != null && refresh_token.length() > 0) {
        try {
          Algorithm algorithm = Algorithm.HMAC256(propertiesConfig.getJwtSecret().getBytes());
          JWTVerifier verifier = JWT.require(algorithm).build();
          DecodedJWT decodedJWT = verifier.verify(refresh_token);
          String email = decodedJWT.getSubject();
          var user = userService.getUserByEmail(email);
          
          String access_token = com.auth0.jwt.JWT.create()
            .withSubject(user.getEmail())
            .withExpiresAt(new Date(System.currentTimeMillis() + propertiesConfig.getJwtAccessExpirationMs())) // 24 hours
            .withIssuer(request.getRequestURL().toString())
            .withClaim("roles", user.getRole().toString())
            .sign(algorithm);
    
          Map<String, Object> data = new HashMap<>();
          data.put("user", user);
          data.put("access_token", access_token);
          response.setContentType(APPLICATION_JSON_VALUE);
          new ObjectMapper().writeValue(response.getOutputStream(), data);

        } catch (Exception e) {
          log.error("Error logging in: {}", e.getMessage());
          response.setHeader("error", e.getMessage());
          response.setStatus(HttpStatus.FORBIDDEN.value());
          Map<String, String> error = new HashMap<>();
          error.put("message", e.getMessage());
          response.setContentType(APPLICATION_JSON_VALUE);
          new ObjectMapper().writeValue(response.getOutputStream(), error);
          throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token", e);
        }
      }
    } else {
      log.error("Refresh token is missing");
      response.setHeader("error", "Refresh token is missing");
      response.setStatus(HttpStatus.FORBIDDEN.value());
      Map<String, String> error = new HashMap<>();
      error.put("message", "Refresh token is missing");
      response.setContentType(APPLICATION_JSON_VALUE);
      new ObjectMapper().writeValue(response.getOutputStream(), error);
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token is missing");
    }
  }
}

