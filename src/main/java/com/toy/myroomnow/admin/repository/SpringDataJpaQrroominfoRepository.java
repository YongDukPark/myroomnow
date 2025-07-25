package com.toy.myroomnow.admin.repository;

import com.toy.myroomnow.admin.domain.Qrroominfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpringDataJpaQrroominfoRepository extends JpaRepository<Qrroominfo, Long>, QrroominfoRepository {

}
