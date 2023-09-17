package com.todaysfail.domains.user.domain;

import com.todaysfail.common.utils.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnimalType {
    DOG("강아지", "https://image.todaysfail.com/default/dog.jpg"),
    KOALA("코알라", "https://image.todaysfail.com/default/koala.jpg"),
    CAT("고양이", "https://image.todaysfail.com/default/cat.jpg"),
    GRASSHOPPER("베짱이", "https://image.todaysfail.com/default/grasshopper.jpg"),
    SQUIRREL("다람쥐", "https://image.todaysfail.com/default/squirrel.jpg"),
    ;

    private String value;
    private String imageUrl;

    public static AnimalType getRandomAnimal() {
        return values()[RandomUtil.getRandomInt(values().length)];
    }
}
