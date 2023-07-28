package com.todaysfail.domains.user.repository;

import com.todaysfail.domains.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {}
