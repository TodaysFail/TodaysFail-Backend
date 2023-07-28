package com.todaysfail.domains.example.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.example.entity.ExampleEntity;
import com.todaysfail.domains.example.exception.NotFoundExampleException;
import com.todaysfail.domains.example.port.ExampleQueryPort;
import com.todaysfail.domains.example.repository.ExampleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExampleQueryAdapter implements ExampleQueryPort {
    private final ExampleJpaRepository exampleJpaRepository;

    @Override
    public ExampleEntity findByExampleId(Long exampleId) {
        return exampleJpaRepository
                .findById(exampleId)
                .orElseThrow(() -> new NotFoundExampleException("Not found example."));
    }
}
