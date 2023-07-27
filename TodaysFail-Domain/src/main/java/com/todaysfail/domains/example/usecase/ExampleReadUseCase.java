package com.todaysfail.domains.example.usecase;

import com.todaysfail.domains.example.domain.Example;

public interface ExampleReadUseCase {
    Example execute(Long exampleId);
}
