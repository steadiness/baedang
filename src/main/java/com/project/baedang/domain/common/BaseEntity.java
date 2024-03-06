package com.project.baedang.domain.common;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseEntity {

    private Long upId;
    private Long regId;
    private LocalDateTime upDt;
    private LocalDateTime regDt;
}
