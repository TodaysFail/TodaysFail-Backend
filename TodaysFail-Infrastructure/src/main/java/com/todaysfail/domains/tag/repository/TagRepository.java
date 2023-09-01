package com.todaysfail.domains.tag.repository;

import com.todaysfail.domains.tag.entity.TagEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Optional<TagEntity> findByTagName(String tagName);
}
