package com.todaysfail.domains.user.repository;

import com.todaysfail.domains.user.entity.AdjectiveNicknameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdjectiveNicknameRepository extends JpaRepository<AdjectiveNicknameEntity, Long> {
    @Query(
            value = "SELECT * FROM tbl_adjective_nickname ORDER BY RAND() LIMIT 1",
            nativeQuery = true)
    AdjectiveNicknameEntity getRandomNickname();
}
