package com.example.namhim;


import com.example.namhim.models.AppUser;
import com.example.namhim.models.Role;
import com.example.namhim.repos.AppUserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class AppUserRepoTest {

    @Autowired
    AppUserRepo appUserRepo;

    @Test
    public void testCreateUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "martin2020";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        AppUser newUser = new AppUser("martin@gmail.com", encodedPassword);
        AppUser savedUser = appUserRepo.save(newUser);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testAssignRolesToUser() {
        Integer userId = 3;

        AppUser appUser = appUserRepo.findById(userId).get();
        appUser.addRole(new Role(2));
        appUser.addRole(new Role(3));

        AppUser updatedUser = appUserRepo.save(appUser);
        assertThat(updatedUser.getRoles()).hasSize(2);
    }
}
