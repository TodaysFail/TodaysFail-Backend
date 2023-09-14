package com.todaysfail.domains.user.repository;

import com.todaysfail.domains.user.domain.RefreshTokenRedisEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshTokenRedisEntity, String> {
    Optional<RefreshTokenRedisEntity> findByRefreshToken(String refreshToken);
}
