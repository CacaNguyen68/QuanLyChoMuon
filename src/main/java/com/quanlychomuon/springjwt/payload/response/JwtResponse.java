package com.quanlychomuon.springjwt.payload.response;

import com.quanlychomuon.springjwt.user.model.Sex;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private String username;
  private String fullName;


  private String phone;

  private String organization;//co quan


  private Sex gender;/*giới tính*/
  private List<String> roles;

  private String agent;


  public JwtResponse(String token, String username, String fullName, String phone, String organization, Sex gender, List<String> roles, String agent) {
    this.token = token;
    this.username = username;
    this.fullName = fullName;
    this.phone = phone;
    this.organization = organization;
    this.gender = gender;
    this.roles = roles;
    this.agent = agent;
  }

  public String getAgent() {
    return agent;
  }

  public void setAgent(String agent) {
    this.agent = agent;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
}
