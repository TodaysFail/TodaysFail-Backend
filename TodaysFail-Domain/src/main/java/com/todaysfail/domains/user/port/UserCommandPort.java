package com.todaysfail.domains.user.port;

import com.todaysfail.domains.user.domain.User;
import java.util.Optional;

public interface UserCommandPort {
    Optional<User> queryUser(Long userId);

    User save(User user);
}
