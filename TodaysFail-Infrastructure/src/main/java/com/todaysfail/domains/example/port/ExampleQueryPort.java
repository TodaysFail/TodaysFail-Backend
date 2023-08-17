package com.todaysfail.domains.example.port;

import com.todaysfail.domains.example.entity.ExampleEntity;

public interface ExampleQueryPort {
    ExampleEntity findByExampleId(Long exampleId);
}
