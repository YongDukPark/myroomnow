package com.toy.myroomnow.admin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "QRROOMINFO")
@Getter
@Setter
public class Qrroominfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long createid;
    private String qrcode;
    private String name;
    private String content;
    private LocalDateTime createat;
    private LocalDateTime updateat;
    private String useyn;

    public Qrroominfo() {
    }

    public Qrroominfo(Long id, Long createid, String qrcode, String name, String content, LocalDateTime createat, LocalDateTime updateat, String useyn) {
        this.id = id;
        this.createid = createid;
        this.qrcode = qrcode;
        this.name = name;
        this.content = content;
        this.createat = createat;
        this.updateat = updateat;
        this.useyn = useyn;
    }
}
