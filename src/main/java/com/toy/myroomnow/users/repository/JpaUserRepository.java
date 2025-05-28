package com.toy.myroomnow.users.repository;

import com.toy.myroomnow.users.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository{

    private final EntityManager em;

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findByUserid(String userid) {
        return Optional.empty();
    }

    @Override
    public boolean existsByBusinessnumber(String businessNumber) {
        return false;
    }
}
