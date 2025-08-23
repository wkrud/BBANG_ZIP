package com.wkrud.rla_bbang_zip.controller;

import com.wkrud.rla_bbang_zip.domain.entity.BbangZip;
import com.wkrud.rla_bbang_zip.domain.repository.BbangZipRepository;
import com.wkrud.rla_bbang_zip.service.BbangZipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bbang-zip")
@Tag(name = "BbangZip", description = "빵집 관련 API")
public class BbangZipController {

    private final BbangZipRepository BbangZipRepository;
    private final BbangZipService bbangZipService;

    @Operation(summary = "모든 빵집 조회", description = "등록된 모든 빵집 정보 조회")
    @GetMapping("get-all")
    public List<BbangZip> getAll() {
        return BbangZipRepository.findAll();
    }

    @Operation(summary = "빵집 등록", description = "새로운 빵집 등록")
    @PostMapping("save-new")
    public BbangZip create(@RequestBody BbangZip bbangZip) {
        return BbangZipRepository.save(bbangZip);
    }

    @Operation(summary = "빵집 등록(중복 체크)", description = "동일한 이름의 빵집이 있으면 반환, 없으면 새로 등록")
    @PostMapping("save-chk")
    public ResponseEntity<?> createBbangZip(@RequestBody BbangZip bbangZip) {
        Optional<BbangZip> existing = bbangZipService.findByName(bbangZip.getName());

        if (existing.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of(
                            "message", "동일한 이름의 빵집이 이미 존재합니다.",
                            "existingBbangZip", existing.get()
                    ));
        }

        BbangZip saved = bbangZipService.createBbangZipIfNotExists(bbangZip);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
