package com.todaysfail.example.mapper;

import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.example.ExampleEntity;
import com.todaysfail.example.domain.Example;

@Mapper
public class ExampleDomainMapper {
	public Example toDomain(ExampleEntity entity) {
		return Example.builder()
			.exampleId(entity.getId())
			.name(entity.getName())
			.build();
	}
}
