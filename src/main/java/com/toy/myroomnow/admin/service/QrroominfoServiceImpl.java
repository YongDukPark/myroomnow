package com.toy.myroomnow.admin.service;

import com.toy.myroomnow.admin.domain.Qrroominfo;
import com.toy.myroomnow.admin.repository.QrroominfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QrroominfoServiceImpl implements QrroominfoService{
    private final QrroominfoRepository qrroominfoRepository;

    @Override
    public Qrroominfo save(Qrroominfo qrroominfo) {
        return qrroominfoRepository.save(qrroominfo);
    }

    @Override
    public void delete(Long id) {
        qrroominfoRepository.deleteById(id);
    }

    @Override
    public List<Qrroominfo> findAllByCreateid(Long createid) {
        List<Qrroominfo> roomlist = qrroominfoRepository.findAllByCreateid(createid);
        return roomlist;
    }

    @Override
    public Optional<Qrroominfo> findByIdAndCreateid(Long id, Long createid) {
        //qrroominfoRepository.findAllByIdAndCreateid(id, createid);
        return null;
    }



    @Override
    public Optional<Qrroominfo> updateByid(Qrroominfo qrroominfo) {
        return Optional.empty();
    }
}
