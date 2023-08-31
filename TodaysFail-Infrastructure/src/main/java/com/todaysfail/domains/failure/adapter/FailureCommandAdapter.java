package com.todaysfail.domains.failure.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.failure.entity.FailureEntity;
import com.todaysfail.domains.failure.port.FailureCommandPort;
import com.todaysfail.domains.failure.repository.FailureRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class FailureCommandAdapter implements FailureCommandPort {
    private final FailureRepository failureRepository;

    @Override
    public FailureEntity registerFailure(
            Long useId,
            Long categoryId,
            LocalDate date,
            String title,
            String content,
            String impression,
            List<Long> tagIdList) {
        FailureEntity failureEntity =
                FailureEntity.registerFailure(
                        useId, categoryId, date, title, content, impression, tagIdList);
        return failureRepository.save(failureEntity);
    }
}
