package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;


// DAO
// 자동으로 Bean 등록
// @Repository 생략 가능
public interface BoardRepository extends JpaRepository<Board, Integer> {
}
