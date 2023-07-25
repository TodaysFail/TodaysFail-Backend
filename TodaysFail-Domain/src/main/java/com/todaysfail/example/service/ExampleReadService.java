package com.todaysfail.example.service;

import com.todaysfail.example.domain.Example;
import com.todaysfail.example.port.ExampleQueryPort;
import com.todaysfail.example.usecase.ExampleReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleReadService implements ExampleReadUseCase {
    private final ExampleQueryPort exampleQueryPort;

    @Override
    public Example execute(Long exampleId) {
        return exampleQueryPort.findByExampleId(exampleId);
    }
}
