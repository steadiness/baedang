package com.project.baedang.domain.user;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class DividendGoal {

    @Id
    @GeneratedValue
    @Column(name = "dividend_goal_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;
}
