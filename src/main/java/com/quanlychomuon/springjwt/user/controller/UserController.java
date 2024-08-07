package com.quanlychomuon.springjwt.user.controller;

import com.quanlychomuon.springjwt.role.model.ERole;
import com.quanlychomuon.springjwt.user.model.Sex;
import com.quanlychomuon.springjwt.user.model.User;
import com.quanlychomuon.springjwt.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  AuthenticationManager authenticationManager;
  @Autowired
  private UserService userService;

  @GetMapping("/search")
  public Page<User> searchUsers(
    @RequestParam(required = false) String username,
    @RequestParam(required = false) String phone,
    @RequestParam(required = false) String organization,
    @RequestParam(required = false) String fullName,
    @RequestParam(required = false) Sex gender,
    @RequestParam(required = false) ERole roleName,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(page, size);
    return userService.searchUsers(username, phone, organization, fullName, gender,roleName, pageable);
  }
}
