package com.todaysfail.domains.nickname.entity;

import com.todaysfail.domains.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "tbl_adjective_nickname")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdjectiveNicknameEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String adjective;

    public AdjectiveNicknameEntity(String adjective) {
        this.adjective = adjective;
    }
}
