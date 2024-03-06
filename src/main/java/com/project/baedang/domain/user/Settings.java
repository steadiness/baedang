package com.project.baedang.domain.user;

import com.project.baedang.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Settings extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "settings_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    private Character notiYn;
    private String language;
    private String theme;
}
