package com.todaysfail.api.web.example.dto.response;

import lombok.Builder;

@Builder
public record ExampleResponse(
	Long exampleId,
	String name
) {
}
