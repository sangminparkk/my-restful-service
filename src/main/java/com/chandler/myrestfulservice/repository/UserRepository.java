package com.chandler.myrestfulservice.repository;

import com.chandler.myrestfulservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
