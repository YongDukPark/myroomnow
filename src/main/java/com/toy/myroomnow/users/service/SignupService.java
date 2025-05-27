package com.toy.myroomnow.users.service;

import com.toy.myroomnow.users.domain.User;
import com.toy.myroomnow.users.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;

//    public User save(User user) {
//
//
//        return user;
//    }

    public User signup(User user) {

        validateDuplicateMember(user);
        userRepository.save(user);

        return user;
    }

    public void validateDuplicateMember(User user) {
        userRepository.findByUserId(user.getUserId())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

}
