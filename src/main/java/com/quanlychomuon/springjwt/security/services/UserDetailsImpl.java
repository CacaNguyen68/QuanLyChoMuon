package com.quanlychomuon.springjwt.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.quanlychomuon.springjwt.user.model.Sex;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.quanlychomuon.springjwt.user.model.User;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String fullName;

  public String getPhone() {
    return phone;
  }

  public String getOrganization() {
    return organization;
  }

  public Sex getGender() {
    return gender;
  }

  private String phone;

  private String organization;//co quan


  private Sex gender;/*giới tính*/

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String username, String fullName,
                         String phone, String organization, Sex gender,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.fullName = fullName;
    this.phone = phone;
    this.organization = organization;
    this.gender = gender;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(),
        user.getUsername(),
        user.getFullName(),
        user.getPhone(),
        user.getOrganization(),
        user.getGender(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  public Long getId() {
    return id;
  }


  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }


  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
