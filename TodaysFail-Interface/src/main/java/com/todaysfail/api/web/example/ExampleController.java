package com.todaysfail.api.web.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todaysfail.api.web.example.dto.response.ExampleResponse;
import com.todaysfail.api.web.example.mapper.ExampleMapper;
import com.todaysfail.example.usecase.ExampleReadUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/examples")
@RequiredArgsConstructor
public class ExampleController {
	private final ExampleReadUseCase exampleReadUseCase;
	private final ExampleMapper exampleMapper;

	@GetMapping("/{example-id}")
	public ExampleResponse getExamples(@PathVariable("example-id") Long exampleId) {
		return exampleMapper.toResponse(exampleReadUseCase.execute(exampleId));
	}
}
