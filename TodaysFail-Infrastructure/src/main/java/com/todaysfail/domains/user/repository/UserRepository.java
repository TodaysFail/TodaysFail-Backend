package com.todaysfail.domains.user.repository;

import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.domains.user.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByOidAndProvider(String oid, OauthProvider provider);

    Boolean existsByOidAndProvider(String oauthId, OauthProvider provider);

    Boolean existsByName(String name);
}
