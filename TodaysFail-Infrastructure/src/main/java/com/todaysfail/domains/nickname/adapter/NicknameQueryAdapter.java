package com.todaysfail.domains.nickname.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.nickname.entity.AdjectiveNicknameEntity;
import com.todaysfail.domains.nickname.entity.AnimalNicknameEntity;
import com.todaysfail.domains.nickname.port.NicknameQueryPort;
import com.todaysfail.domains.nickname.repository.AdjectiveNicknameRepository;
import com.todaysfail.domains.nickname.repository.AnimalNicknameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NicknameQueryAdapter implements NicknameQueryPort {
    private final AdjectiveNicknameRepository adjectiveNicknameRepository;
    private final AnimalNicknameRepository animalNicknameRepository;

    @Override
    public AdjectiveNicknameEntity queryRandomAdjectiveNickName() {
        return adjectiveNicknameRepository.getRandomNickname();
    }

    @Override
    public AnimalNicknameEntity queryRandomAnimalNickName() {
        return animalNicknameRepository.getRandomNickname();
    }
}
