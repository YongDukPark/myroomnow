package com.toy.myroomnow.users.security;

import com.toy.myroomnow.users.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // 로그인 ID
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // 계정 만료 여부
    @Override public boolean isAccountNonExpired() { return true; }

    // 계정 잠김 여부
    @Override public boolean isAccountNonLocked() { return true; }

    // 비밀번호 만료 여부
    @Override public boolean isCredentialsNonExpired() { return true; }

    // 계정 활성화 여부
    @Override public boolean isEnabled() { return true; }

    // 💡 추가 사용자 정보 접근자
    public String getUserid() {
        return user.getUserid();
    }

    public String getPhoneNumber() {
        return user.getPhonenumber();
    }

    public String getGender() {
        return user.getGender();
    }

    public String getBirthdate() {
        return user.getBirthdate();
    }

    public String getBusinessNumber() {
        return user.getBusinessnumber();
    }

    public User getUserEntity() {
        return user;
    }
}
