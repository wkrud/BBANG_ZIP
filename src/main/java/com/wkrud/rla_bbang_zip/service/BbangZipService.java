package com.wkrud.rla_bbang_zip.service;

import com.wkrud.rla_bbang_zip.domain.entity.BbangZip;
import com.wkrud.rla_bbang_zip.domain.repository.BbangZipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BbangZipService {

    private final BbangZipRepository bbangZipRepository;

    @Transactional(readOnly = true)
    public Optional<BbangZip> findByName(String bbangZipName) {
        return bbangZipRepository.findByName(bbangZipName);
    }

    @Transactional
    public BbangZip createBbangZipIfNotExists(BbangZip newBbangZip) {
        return bbangZipRepository.findByName(newBbangZip.getName())
                .orElseGet(() -> bbangZipRepository.save(newBbangZip));
    }

}
