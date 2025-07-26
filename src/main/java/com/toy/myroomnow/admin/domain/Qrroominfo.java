package com.toy.myroomnow.admin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "QRROOMINFO")
@Getter
@Setter
@NoArgsConstructor
public class Qrroominfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createid;
    private String type;
    private String qrcode;
    private String name;
    private String content;
    private LocalDateTime createat;
    private LocalDateTime updateat;
    private String useyn;
}
