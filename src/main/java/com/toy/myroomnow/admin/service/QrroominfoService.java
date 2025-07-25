package com.toy.myroomnow.admin.service;

import com.toy.myroomnow.admin.domain.Qrroominfo;

import java.util.List;
import java.util.Optional;

public interface QrroominfoService {
    // 생성
    Qrroominfo save(Qrroominfo qrroominfo);
    // 삭제
    void delete(Long id);
    // 특정 계정 방정보 조회
    List<Qrroominfo> findAllByCreateid(Long createid);
    // 특정 계정 방정보 디테일 조회
    Optional<Qrroominfo> findByIdAndCreateid(Long id, Long createid);
    // 수정
    Optional<Qrroominfo> updateByid(Qrroominfo qrroominfo);
}
