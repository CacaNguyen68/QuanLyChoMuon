package com.quanlychomuon.springjwt.user.repository;

import java.util.List;
import java.util.Optional;

import com.quanlychomuon.springjwt.role.model.ERole;
import com.quanlychomuon.springjwt.user.model.Sex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quanlychomuon.springjwt.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);


  @Query("SELECT u FROM User u JOIN u.roles r WHERE " +
    "(:username IS NULL OR u.username LIKE %:username%) AND " +
    "(:phone IS NULL OR u.phone LIKE %:phone%) AND " +
    "(:organization IS NULL OR u.organization LIKE %:organization%) AND " +
    "(:fullName IS NULL OR u.fullName LIKE %:fullName%) AND " +
    "(:gender IS NULL OR u.gender = :gender) AND " +
    "(:roleName IS NULL OR r.name = :roleName)")
  Page<User> searchUsers(
    @Param("username") String username,
    @Param("phone") String phone,
    @Param("organization") String organization,
    @Param("fullName") String fullName,
    @Param("gender") Sex gender,
    @Param("roleName") ERole roleName,
    Pageable pageable);
}
