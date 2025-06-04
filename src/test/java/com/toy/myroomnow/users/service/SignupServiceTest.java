package com.toy.myroomnow.users.service;

import com.toy.myroomnow.config.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SignupServiceTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    void passwordEncoder(){
        BCryptPasswordEncoder bean = ac.getBean(BCryptPasswordEncoder.class);
        String password = bean.encode("adm");

        //Assertions.assertThat(password).isEqualTo("adm");
        Assertions.assertThat(password).isEqualTo("$2a$10$1CLqDLs2B38nRiK2KzlqP.ZdTBFxWIZ/Se7V5mRYgnTk70mLyK1z2");
    }

}