package com.quanlychomuon.springjwt.payload.request;

import java.util.Set;

import com.quanlychomuon.springjwt.user.model.Sex;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public class SignupRequest {

  private String username;

  @NotBlank(message = "Password not to empty!")
  private String password;

  @NotBlank(message = "Phone not to empty!")
  private String phone;

  @NotBlank(message = "Birthday not to empty!")
  private String birthday;

  private String organization;//co quan

  @NotBlank(message = "Name not to empty!")
  private String fullName;

  private Sex gender;/*giới tính*/

  @Column(name = "note")
  private String note;/*ghi chú*/

  private Set<String> role;



  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Sex getGender() {
    return gender;
  }

  public void setGender(Sex gender) {
    this.gender = gender;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public Set<String> getRole() {
    return this.role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
}
