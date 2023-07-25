package com.todaysfail.api.web.example;

import com.todaysfail.api.web.example.dto.response.ExampleResponse;
import com.todaysfail.api.web.example.mapper.ExampleMapper;
import com.todaysfail.example.usecase.ExampleReadUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "X. Example")
@RestController
@RequestMapping("/api/v1/examples")
@RequiredArgsConstructor
public class ExampleController {
    private final ExampleReadUseCase exampleReadUseCase;
    private final ExampleMapper exampleMapper;

    @Operation(summary = "Example 조회 API")
    @GetMapping("/{example-id}")
    public ExampleResponse getExamples(@PathVariable("example-id") Long exampleId) {
        return exampleMapper.toResponse(exampleReadUseCase.execute(exampleId));
    }
}
