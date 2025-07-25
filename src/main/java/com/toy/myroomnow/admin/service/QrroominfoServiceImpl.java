package com.toy.myroomnow.admin.service;

import com.toy.myroomnow.admin.domain.Qrroominfo;
import com.toy.myroomnow.admin.dto.QrroominfoDto;
import com.toy.myroomnow.admin.repository.QrroominfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<QrroominfoDto> findAllByCreateid(Long createid) {
        List<Qrroominfo> roomlist = qrroominfoRepository.findAllByCreateid(createid);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return roomlist.stream()
                .map(qr -> new QrroominfoDto(
                        qr.getId(),
                        qr.getCreateid(),
                        qr.getQrcode(),
                        qr.getName(),
                        qr.getContent(),
                        formatDate(qr.getCreateat()),
                        formatDate(qr.getUpdateat()),
                        qr.getUseyn()
                ))
                .collect(Collectors.toList());
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

    private static String formatDate(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : null;
    }
}
