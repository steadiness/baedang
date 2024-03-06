package com.project.baedang.domain.user;

import com.project.baedang.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
public class User extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "users_id")
    private Long id;

    private String loginId;
    private String pwd;

    private String nickName;
    private String userName;

    private String country;
    private String timezone;
    private String email;

    @Enumerated(EnumType.STRING)
    private AuthStatus auth;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private Character useYn;

    @OneToOne(mappedBy = "settings", fetch = FetchType.LAZY)
    private Settings settings;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Notification> notifications = new ArrayList<>();

}
