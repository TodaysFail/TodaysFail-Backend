package com.todaysfail.domains.user.repository;

import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByOauthInfo(OauthInfo oauthInfo);

    Boolean existsByOauthInfo(OauthInfo oauthInfo);

    Boolean existsByProfileName(String name);
}
