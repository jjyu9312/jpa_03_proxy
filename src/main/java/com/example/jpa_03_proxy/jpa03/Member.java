package com.example.jpa_03_proxy.jpa03;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME") // 데이터베이스와 컬럼명 매칭
    private String username;

    // 1:N 양방향에 사용
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // 연관관계 주인처럼 만들었지만 읽기 전용 필드로 사용
    private Team team;

}
