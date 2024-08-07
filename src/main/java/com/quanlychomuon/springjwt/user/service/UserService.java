package com.quanlychomuon.springjwt.user.service;

import com.quanlychomuon.springjwt.role.model.ERole;
import com.quanlychomuon.springjwt.user.model.Sex;
import com.quanlychomuon.springjwt.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
  Page<User> searchUsers(String username, String phone, String organization, String fullName, Sex gender, ERole roleName, Pageable pageable);
}
