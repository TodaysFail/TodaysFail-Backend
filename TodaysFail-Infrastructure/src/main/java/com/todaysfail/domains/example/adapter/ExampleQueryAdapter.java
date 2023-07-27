package com.todaysfail.domains.example.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.example.ExampleEntity;
import com.todaysfail.domains.example.domain.Example;
import com.todaysfail.domains.example.exception.NotFoundExampleException;
import com.todaysfail.domains.example.mapper.ExampleInfraMapper;
import com.todaysfail.domains.example.port.ExampleQueryPort;
import com.todaysfail.domains.example.repository.ExampleJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExampleQueryAdapter implements ExampleQueryPort {
    private final ExampleInfraMapper exampleInfraMapper;
    private final ExampleJpaRepository exampleJpaRepository;

    @Override
    public Example findByExampleId(Long exampleId) {
        ExampleEntity exampleEntity =
                exampleJpaRepository
                        .findById(exampleId)
                        .orElseThrow(() -> new NotFoundExampleException("Not found example."));
        return exampleInfraMapper.toDomain(exampleEntity);
    }
}
