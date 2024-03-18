package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// DAO
// 자동으로 Bean 등록
// @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {
    // JPA 네이밍 전략
    // SELECT * FROM user WHERE username = ?1 AND password = ?2
    User findByUsernameAndPassword(String username, String password); // (?1, ?2) = (String username, String password)

    // @Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
    // User login(String username, String password);
}

