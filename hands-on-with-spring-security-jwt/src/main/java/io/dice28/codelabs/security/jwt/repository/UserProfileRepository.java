package io.dice28.codelabs.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.dice28.codelabs.security.jwt.repository.entity.UserProfileEntity;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Integer> {
    UserProfileEntity findByUsername(String username);
}
