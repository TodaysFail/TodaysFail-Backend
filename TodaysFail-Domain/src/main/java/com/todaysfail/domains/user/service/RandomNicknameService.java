package com.todaysfail.domains.user.service;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.utils.RandomUtil;
import com.todaysfail.domains.user.domain.Nickname;
import com.todaysfail.domains.user.entity.AnimalNicknameEntity;
import com.todaysfail.domains.user.exception.NicknameGenerationFailedException;
import com.todaysfail.domains.user.port.NicknameQueryPort;
import com.todaysfail.domains.user.port.UserQueryPort;
import com.todaysfail.domains.user.usecase.RandomNicknameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RandomNicknameService implements RandomNicknameUseCase {
    private final NicknameQueryPort nicknameQueryPort;
    private final UserQueryPort userQueryPort;

    @Override
    public Nickname execute() {
        for (int attempt = 1; attempt <= NICKNAME_GENERATE_MAX_ATTEMPTS; attempt++) {
            String adjectiveNickName =
                    nicknameQueryPort.queryRandomAdjectiveNickName().getAdjective();
            AnimalNicknameEntity animalNicknameEntity =
                    nicknameQueryPort.queryRandomAnimalNickName();
            String animalNickName = animalNicknameEntity.getAnimal();

            String generatedNickName =
                    adjectiveNickName + "_" + animalNickName + "_" + RandomUtil.getRandomInt(1000);

            if (!userQueryPort.existsByName(generatedNickName)) {
                return Nickname.of(generatedNickName, animalNicknameEntity.getProfileImageUrl());
            }
        }
        throw NicknameGenerationFailedException.EXCEPTION;
    }
}
