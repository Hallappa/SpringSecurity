package com.securit.SpringSecurity.repository;

import com.securit.SpringSecurity.doa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  public User findByUsername(String username);
}

