package com.todaysfail.domains.example.port;

import com.todaysfail.domains.example.domain.Example;

public interface ExampleQueryPort {
    Example findByExampleId(Long exampleId);
}
