package com.chandler.myrestfulservice.repository;

import com.chandler.myrestfulservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
