package com.todaysfail.domains.user.entity;

import com.todaysfail.domains.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "tbl_animal_nickname")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnimalNicknameEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String animal;

    private String profileImageUrl;

    public AnimalNicknameEntity(String animal, String profileImageUrl) {
        this.animal = animal;
        this.profileImageUrl = profileImageUrl;
    }
}
