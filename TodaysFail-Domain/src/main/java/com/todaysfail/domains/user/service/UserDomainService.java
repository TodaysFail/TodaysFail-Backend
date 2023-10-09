package com.todaysfail.domains.user.service;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.utils.RandomUtil;
import com.todaysfail.domains.user.domain.AdjectiveType;
import com.todaysfail.domains.user.domain.AnimalType;
import com.todaysfail.domains.user.domain.Nickname;
import com.todaysfail.domains.user.exception.NicknameGenerationFailedException;
import com.todaysfail.domains.user.port.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDomainService {
    private final UserQueryPort userQueryPort;

    public Nickname randomNicknameGeneration() {
        for (int attempt = 1; attempt <= NICKNAME_GENERATE_MAX_ATTEMPTS; attempt++) {

            AdjectiveType randomAdjective = AdjectiveType.getRandomAdjective();
            AnimalType randomAnimal = AnimalType.getRandomAnimal();

            String generatedNickName =
                    randomAdjective.getValue()
                            + "_"
                            + randomAnimal.getValue()
                            + "_"
                            + RandomUtil.getRandomInt(1000);

            if (!userQueryPort.existsByName(generatedNickName)) {
                return Nickname.builder()
                        .nickname(generatedNickName)
                        .profileImageUrl(randomAnimal.getImageUrl())
                        .build();
            }
        }
        throw NicknameGenerationFailedException.EXCEPTION;
    }
}
