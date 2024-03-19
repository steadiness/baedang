package com.project.baedang.domain.commnunity;

import com.project.baedang.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class News extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private Long id;

    private String title;

    private String content;

    private LocalDateTime publishDate;

    private String Source;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();

}
