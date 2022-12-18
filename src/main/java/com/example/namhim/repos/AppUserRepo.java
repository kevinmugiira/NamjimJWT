package com.example.namhim.repos;

import com.example.namhim.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
}
