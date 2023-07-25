package com.todaysfail.api.web.example.mapper;

import com.todaysfail.api.web.example.dto.response.ExampleResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.example.domain.Example;

@Mapper
public class ExampleMapper {
    public ExampleResponse toResponse(final Example domain) {
        return ExampleResponse.builder().exampleId(domain.exampleId()).name(domain.name()).build();
    }
}
