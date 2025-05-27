package com.toy.myroomnow.users.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userId")
    private String userId;    // 사용자 ID 또는 이메일
    @Column(name = "password")
    private String password;    // 비밀번호
    private String confirmPassword; // 비밀번호 확인 (프론트에서만 사용)
    @Column(name = "userName")
    private String userName;        // 이름
    @Column(name = "phoneNumber")
    private String phoneNumber; // 전화번호
    @Column(name = "gender")
    private String gender;      // 성별
    @Column(name = "birtDate")
    private String birthDate; // 생년월일
    @Column(name = "businessNumber")
    private String businessNumber;

    public User(String userId, String password, String userName, String phoneNumber, String gender, String birthDate, String businessNumber) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthDate = birthDate;
        this.businessNumber = businessNumber;
    }
}
