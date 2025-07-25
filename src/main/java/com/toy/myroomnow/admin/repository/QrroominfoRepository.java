package com.toy.myroomnow.admin.repository;

import com.toy.myroomnow.admin.domain.Qrroominfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface QrroominfoRepository extends JpaRepository<Qrroominfo, Long>{
    // 특정 계정 방정보 조회
    List<Qrroominfo> findAllByCreateid(Long createid);

    // 특정 계정 특정 방정보 조회
    List<Qrroominfo> findAllByIdAndCreateid(Long id, Long createid);

//    // 수정
//    Optional<Qrroominfo> updateByid(Qrroominfo qrroominfo);
}
