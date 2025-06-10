package com.toy.myroomnow.config;

import com.toy.myroomnow.users.repository.UserRepository;
import com.toy.myroomnow.users.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 개발 중에는 CSRF도 꺼줄 수 있음
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 모든 요청 허용
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/admin/index", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                        // 🔒 기본 로그인 페이지 비활성화
                .httpBasic().disable(); // 🔒 기본 인증 팝업도 비활성화

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("/login") //로그인 폼
//                .loginProcessingUrl("/login") // POST 요청 처리 경로
//                .defaultSuccessUrl("/admin/index", true) // 성공 시
//                .failureUrl("/login?error=true")
//                .permitAll();
//    }

}
