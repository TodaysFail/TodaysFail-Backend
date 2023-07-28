package com.todaysfail.domains.example.service;

import com.todaysfail.domains.example.domain.Example;
import com.todaysfail.domains.example.entity.ExampleEntity;
import com.todaysfail.domains.example.port.ExampleQueryPort;
import com.todaysfail.domains.example.usecase.ExampleReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleReadService implements ExampleReadUseCase {
    private final ExampleQueryPort exampleQueryPort;

    @Override
    public Example execute(Long exampleId) {
        ExampleEntity exampleEntity = exampleQueryPort.findByExampleId(exampleId);
        return Example.builder()
                .exampleId(exampleEntity.getId())
                .name(exampleEntity.getName())
                .build();
    }
}
