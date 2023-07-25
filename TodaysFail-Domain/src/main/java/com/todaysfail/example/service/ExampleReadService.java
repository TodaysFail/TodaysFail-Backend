package com.todaysfail.example.service;

import com.todaysfail.example.domain.Example;
import com.todaysfail.example.mapper.ExampleDomainMapper;
import com.todaysfail.example.port.ExampleQueryPort;
import com.todaysfail.example.usecase.ExampleReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleReadService implements ExampleReadUseCase {
    private final ExampleDomainMapper exampleDomainMapper;
    private final ExampleQueryPort exampleQueryPort;

    @Override
    public Example execute(Long exampleId) {
        return exampleDomainMapper.toDomain(exampleQueryPort.findByExampleId(exampleId));
    }
}
