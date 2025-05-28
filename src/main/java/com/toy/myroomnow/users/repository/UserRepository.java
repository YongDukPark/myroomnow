package com.toy.myroomnow.users.repository;

import com.toy.myroomnow.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository {
    // 회원가입
    User save(User user);
    // 사용자 ID로 찾기 (ex. 로그인, 중복검사)
    Optional<User> findByUserid(String userId);
    // 이름 + 전화번호로 찾기 (ex. 아이디 찾기 기능)
    //Optional<User> findByUserNameAndPhoneNumber(String userName, String phoneNumber);
    // 사업자 번호로 중복 체크
    boolean existsByBusinessnumber(String businessNumber);
}
