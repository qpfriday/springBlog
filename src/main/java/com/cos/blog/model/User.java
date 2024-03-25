package com.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

// ORM -> Java Object 를 테이블로 만들어줌
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // User 클래스가 MySQL 에 테이블 생성
// @DynamicInsert -> insert 시 null 인 필드 제외
public class User {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB 의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 100, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault("user")
    @Enumerated(EnumType.STRING)// DB 는 RoleType 이 없다
    private RoleType role; // Enum 을 쓰는게 좋다. ADMIN, USER

    private String oauth;

    @CreationTimestamp // 시간 자동 입력
    private Timestamp createDate;
}
