package com.wkrud.rla_bbang_zip.domain.repository;

import com.wkrud.rla_bbang_zip.domain.entity.BbangZip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BbangZipRepository extends JpaRepository<BbangZip, Long> {
    Optional<BbangZip> findByName(String name);
}
