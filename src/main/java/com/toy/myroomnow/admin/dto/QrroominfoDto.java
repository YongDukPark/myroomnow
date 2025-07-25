package com.toy.myroomnow.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QrroominfoDto {
    private Long id;
    private Long createid;
    private String qrcode;
    private String name;
    private String content;
    private String createat;
    private String updateat;
    private String useyn;
}
