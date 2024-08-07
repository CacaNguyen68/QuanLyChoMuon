package com.quanlychomuon.springjwt.seeder;

import com.quanlychomuon.springjwt.role.model.ERole;
import com.quanlychomuon.springjwt.role.model.Role;
import com.quanlychomuon.springjwt.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeederData implements CommandLineRunner {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {
    if (roleRepository.count() == 0) {
      // Insert roles if the table is empty
      Role userRole = new Role(ERole.ROLE_USER);
      Role superRole = new Role(ERole.ROLE_SUPER);
      Role adminRole = new Role(ERole.ROLE_ADMIN);

      roleRepository.saveAll(List.of(userRole, superRole, adminRole));
      System.out.println("Default roles inserted into the database");
    }
  }
}
