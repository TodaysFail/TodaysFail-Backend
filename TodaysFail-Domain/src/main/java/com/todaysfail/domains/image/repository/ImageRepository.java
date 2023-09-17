package com.todaysfail.domains.image.repository;

import com.todaysfail.domains.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {}
