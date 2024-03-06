package com.project.baedang.domain.user;

import com.project.baedang.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "notification_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    private String content;
    private String notiType;
    private LocalDateTime notiDt;
}
