package com.todaysfail.domains.failure.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.failure.exception.FailureNotFoundException;
import com.todaysfail.domains.failure.port.FailureCommandPort;
import com.todaysfail.domains.failure.repository.FailureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class FailureCommandAdapter implements FailureCommandPort {
    private final FailureRepository failureRepository;

    @Override
    public Failure save(Failure failure) {
        return failureRepository.save(failure);
    }

    @Override
    public Failure queryFailure(Long failureId) {
        return failureRepository
                .findById(failureId)
                .orElseThrow(() -> FailureNotFoundException.EXCEPTION);
    }

    @Override
    public void delete(Failure failure) {
        failureRepository.delete(failure);
    }
}
