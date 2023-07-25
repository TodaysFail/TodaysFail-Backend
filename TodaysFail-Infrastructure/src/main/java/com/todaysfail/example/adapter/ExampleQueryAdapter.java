package com.todaysfail.example.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.example.ExampleEntity;
import com.todaysfail.example.domain.Example;
import com.todaysfail.example.exception.NotFoundExampleException;
import com.todaysfail.example.mapper.ExampleInfraMapper;
import com.todaysfail.example.port.ExampleQueryPort;
import com.todaysfail.example.repository.ExampleJpaRepository;
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
