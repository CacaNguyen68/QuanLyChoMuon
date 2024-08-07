package com.quanlychomuon.springjwt.user.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quanlychomuon.springjwt.role.model.Role;
import com.quanlychomuon.springjwt.utils.DateAudit;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "username"),
    })
public class User extends DateAudit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false)
  @NotBlank(message = "Username not to empty!")
  private String username;

  @Column(name = "password", nullable = false)
  @NotBlank(message = "Password not to empty!")
  @JsonIgnore//khong cho hien thi trong Json
  private String password;

  @Column(name = "phone", nullable = false, length = 10)
  @NotBlank(message = "Phone not to empty!")
  private String phone;

  @Column(name = "birthday", nullable = false)
  @NotBlank(message = "Birthday not to empty!")
  private String birthday;

  @Column(name = "organization", nullable = false)
  private String organization;//co quan

  @Column(name = "full_name", nullable = false)
  @NotBlank(message = "Name not to empty!")
  private String fullName;

  @Column(name = "gender")
  @Comment("0: male; 1: female")
  @Enumerated(EnumType.ORDINAL)
  private Sex gender;/*giới tính*/

  @Column(name = "image")
  private String image;/*ghi chú*/

  @Column(name = "note")
  private String note;/*ghi chú*/

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String password, String phone, String birthday, String organization, String fullName, Sex gender, String note, String image) {
    this.username = username;
    this.password = password;
    this.phone = phone;
    this.birthday = birthday;
    this.organization = organization;
    this.fullName = fullName;
    this.gender = gender;
    this.note = note;
    this.image = image;
  }

  //  public User(String username, String email, String password) {
//    this.username = username;
//    this. = email;
//    this.password = password;
//  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
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

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
}
