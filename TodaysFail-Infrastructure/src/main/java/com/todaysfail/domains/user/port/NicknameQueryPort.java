package com.todaysfail.domains.user.port;

import com.todaysfail.domains.user.entity.AdjectiveNicknameEntity;
import com.todaysfail.domains.user.entity.AnimalNicknameEntity;

public interface NicknameQueryPort {
    AdjectiveNicknameEntity queryRandomAdjectiveNickName();

    AnimalNicknameEntity queryRandomAnimalNickName();
}
