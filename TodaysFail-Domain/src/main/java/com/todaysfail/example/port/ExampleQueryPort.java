package com.todaysfail.example.port;

import com.todaysfail.example.domain.Example;

public interface ExampleQueryPort {
    Example findByExampleId(Long exampleId);
}
