package com.todaysfail.domains.tag.repository;

import com.todaysfail.domains.tag.domain.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagName(String tagName);

    List<Tag> findTop5ByTagNameContainsIgnoreCase(String searchKeyword);

    List<Tag> findTop5ByOrderByUsedCountDesc();
}
