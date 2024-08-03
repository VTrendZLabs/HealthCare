package com.vtrendz.app.healthcare.user;

import com.vtrendz.app.healthcare.security.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @Autowired
  private UserRepository repository;

  @GetMapping("/all")
  public List<String> findAllUsers() {
    List<User> users = repository.findAll();
    return users.stream().map(user -> user.getFirstName() + " " + user.getLastName()).collect(Collectors.toList());
  }

  @PatchMapping
  public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
    service.changePassword(request, connectedUser);
    return ResponseEntity.ok().build();
  }
}
