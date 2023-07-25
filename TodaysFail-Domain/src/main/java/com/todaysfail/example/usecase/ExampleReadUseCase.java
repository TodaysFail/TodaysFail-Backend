package com.todaysfail.example.usecase;

import com.todaysfail.example.domain.Example;

public interface ExampleReadUseCase {
	Example execute(Long exampleId);
}
