package com.quanlychomuon.springjwt.user.service.impl;

import com.quanlychomuon.springjwt.role.model.ERole;
import com.quanlychomuon.springjwt.role.model.Role;
import com.quanlychomuon.springjwt.user.model.Sex;
import com.quanlychomuon.springjwt.user.model.User;
import com.quanlychomuon.springjwt.user.repository.UserRepository;
import com.quanlychomuon.springjwt.user.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @PersistenceContext
  private EntityManager entityManager;

  public Page<User> searchUsers(String username, String phone, String organization, String fullName, Sex gender, ERole roleName, Pageable pageable) {
    return userRepository.searchUsers(username, phone, organization, fullName, gender, roleName, pageable);
  }
}
