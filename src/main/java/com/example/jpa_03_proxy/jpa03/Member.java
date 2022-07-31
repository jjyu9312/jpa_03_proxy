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

//    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY => 지연 로딩
    @ManyToOne(fetch = FetchType.EAGER)  // FetchType.EAGER => 즉시 로딩
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false) // fetch = FetchType.LAZY 있으면 프록시 객체로 조회
    private Team team;
}
