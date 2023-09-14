package com.todaysfail.domains.user.domain;

import com.todaysfail.common.utils.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdjectiveType {
    I_M_SCARED_OF_FAILURE("실패가 무서운"),
    RECORDS_ARE_ANNOYING("기록이 귀찮은"),
    CONFIDENT_IN_FAILURE("실패에 당당한"),
    TODAY_I_FAILED("오늘은 실패한"),
    TOMORROW_WILL_BE_SUCCESSFUL("내일은 성공할"),
    ;

    private String value;

    public static AdjectiveType getRandomAdjective() {
        return values()[RandomUtil.getRandomInt(values().length)];
    }
}
