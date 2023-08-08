package com.todaysfail.domains.nickname.port;

import com.todaysfail.domains.nickname.entity.AdjectiveNicknameEntity;
import com.todaysfail.domains.nickname.entity.AnimalNicknameEntity;

public interface NicknameQueryPort {
    AdjectiveNicknameEntity queryRandomAdjectiveNickName();

    AnimalNicknameEntity queryRandomAnimalNickName();
}
