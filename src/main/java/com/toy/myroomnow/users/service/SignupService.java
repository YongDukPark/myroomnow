package com.toy.myroomnow.users.service;

import com.toy.myroomnow.users.domain.User;
import com.toy.myroomnow.users.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    String password;
//    public User save(User user) {
//
//
//        return user;
//    }

    public User signup(User user) {
        String password = user.getPassword();

        //암호화 로직
        user.setPassword(passwordEncoder(password));

        validateDuplicateMember(user);
        userRepository.save(user);

        return user;
    }

    public String passwordEncoder(String Password){
        String encodedPassword = passwordEncoder.encode(Password);

        return encodedPassword;
    }

    public void validateDuplicateMember(User user) {
        userRepository.findByUserid(user.getUserid())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    public boolean existsByUserid(String userid) {
        return userRepository.existsByUserid(userid);
    }
}
