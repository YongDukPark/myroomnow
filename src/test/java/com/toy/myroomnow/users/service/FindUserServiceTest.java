package com.toy.myroomnow.users.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class FindUserServiceTest {
    @Test
    public void createAuthCode(){
        StringBuilder authcode = new StringBuilder();
        Random random = new Random();
        for (int i = 0 ; i < 6 ; i++) {
            authcode.append(Integer.toString(random.nextInt(10)));
        }

        Assertions.assertThat(authcode.length()).isEqualTo(6);
    }
}