package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을 해줌. IoC 를 해줌
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
        // public int 회원가입(User user) {
        // try {
        //     userRepository.save(user);
        //     return 1;
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     System.out.println("UserService : 회원가입() : " + e.getMessage());
        // }
        // return -1;
    }

    @Transactional(readOnly = true) // Select 할 때 트랜잭션 시작. 해당 서비스가 종료될 때 트랜잭션 종료. (정합성 유지)
    public User 로그인(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
