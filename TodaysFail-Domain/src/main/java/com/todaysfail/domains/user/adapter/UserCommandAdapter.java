package com.todaysfail.domains.user.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.UserCommandPort;
import com.todaysfail.domains.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class UserCommandAdapter implements UserCommandPort {
    private final UserRepository userRepository;

    @Override
    public Optional<User> queryUser(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
