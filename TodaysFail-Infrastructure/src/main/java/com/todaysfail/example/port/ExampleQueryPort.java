package com.todaysfail.example.port;

import com.todaysfail.example.ExampleEntity;

public interface ExampleQueryPort {
	ExampleEntity findByExampleId(Long exampleId);
}
