package com.todaysfail.domains.image.repository;

import com.todaysfail.domains.image.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {}
