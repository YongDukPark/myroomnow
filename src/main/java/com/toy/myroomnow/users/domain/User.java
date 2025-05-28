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
    //@Column(name = "userId")
    private String userid;    // 사용자 ID 또는 이메일
    //@Column(name = "password")
    private String password;    // 비밀번호
    //private String confirmpassword; // 비밀번호 확인 (프론트에서만 사용)
    //@Column(name = "userName")
    private String username;        // 이름
    //@Column(name = "phoneNumber")
    private String phonenumber; // 전화번호
    //@Column(name = "gender")
    private String gender;      // 성별
    //@Column(name = "birthDate")
    private String birthdate; // 생년월일
    //@Column(name = "businessNumber")
    private String businessnumber;

    public User(Long id, String userid, String password, String username, String phonenumber, String gender, String birthdate, String businessnumber) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.phonenumber = phonenumber;
        this.gender = gender;
        this.birthdate = birthdate;
        this.businessnumber = businessnumber;
    }
}
