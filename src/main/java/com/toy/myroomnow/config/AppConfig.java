package com.toy.myroomnow.config;

import com.toy.myroomnow.users.repository.UserRepository;
import com.toy.myroomnow.users.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

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
                        .successHandler((request, response, authentication) -> {
                            System.out.println("✅ 로그인 성공");
                            response.sendRedirect("/admin/index");
                        })
                        .failureHandler((request, response, exception) -> {
                            System.out.println("❌ 로그인 실패: " + exception.getMessage());
                            response.sendRedirect("/login?error=true");
                        })
                        .permitAll()
                )
                // 🔒 기본 로그인 페이지 비활성화
                .httpBasic().disable(); // 🔒 기본 인증 팝업도 비활성화

        return http.build();
    }

    //인증 처리 매니저를 명시적으로 구현 하는 영역
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new UserDetailsServiceImpl(userRepository);
    }
}
