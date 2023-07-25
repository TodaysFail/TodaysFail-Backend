package com.todaysfail.example.domain;

import lombok.Builder;

@Builder
public record Example(Long exampleId, String name) {}
