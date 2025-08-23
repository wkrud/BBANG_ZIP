package com.wkrud.rla_bbang_zip.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bbang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(name="bbang_name", nullable = false)
    private String bbangName;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY) // 즉시로딩 시 예상치 못한 SQL이 발생 할 수 있으므로 지연로딩
    @JoinColumn(name = "bbang_zip_id")
    private BbangZip bbangZip;

    private int rankScore;

}
