package com.todaysfail.domains.user.entity;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("refreshToken")
@AllArgsConstructor
public class RefreshTokenRedisEntity {
    @Id private Long id;

    @Indexed private String refreshToken;

    @TimeToLive private Long ttl;
}
