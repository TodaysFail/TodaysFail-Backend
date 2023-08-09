package com.todaysfail.domains.user.repository;

import com.todaysfail.domains.user.entity.AnimalNicknameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalNicknameRepository extends JpaRepository<AnimalNicknameEntity, Long> {
    @Query(value = "SELECT * FROM tbl_animal_nickname ORDER BY RAND() LIMIT 1", nativeQuery = true)
    AnimalNicknameEntity getRandomNickname();
}
