package com.todaysfail.example.mapper;

import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.example.ExampleEntity;
import com.todaysfail.example.domain.Example;

@Mapper
public class ExampleInfraMapper {
    public Example toDomain(final ExampleEntity entity) {
        return Example.builder().exampleId(entity.getId()).name(entity.getName()).build();
    }
}
