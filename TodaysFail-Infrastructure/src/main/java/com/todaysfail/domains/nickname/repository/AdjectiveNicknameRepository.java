package com.todaysfail.domains.nickname.repository;

import com.todaysfail.domains.nickname.entity.AdjectiveNicknameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdjectiveNicknameRepository extends JpaRepository<AdjectiveNicknameEntity, Long> {
    @Query(
            value = "SELECT * FROM tbl_adjective_nickname ORDER BY RAND() LIMIT 1",
            nativeQuery = true)
    AdjectiveNicknameEntity getRandomNickname();
}
