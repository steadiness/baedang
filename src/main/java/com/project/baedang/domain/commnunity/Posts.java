package com.project.baedang.domain.commnunity;

import com.project.baedang.domain.common.BaseEntity;
import com.project.baedang.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Posts extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "posts_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    private LocalDateTime create_date;

    private Integer views;
    private Integer likes;
    private Integer hates;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

}
