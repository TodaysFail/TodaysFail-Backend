package com.todaysfail.domains.example.domain;

import lombok.Builder;

@Builder
public record Example(Long exampleId, String name) {}
