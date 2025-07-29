package com.toy.myroomnow.users.repository;

import com.toy.myroomnow.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaUserRepository extends JpaRepository<User, Long>, UserRepository{
    @Override
    Optional<User> findByUserid(String userId);

    // 아이디 찾기
    @Override
    Optional<User> findByPhonenumber(String phonenumber);
    // 비밀번호 찾기
    @Override
    Optional<User> findByUseridAndPhonenumber(String userId, String phonenumber);

}
