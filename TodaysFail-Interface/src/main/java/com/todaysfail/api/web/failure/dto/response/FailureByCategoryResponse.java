package com.todaysfail.api.web.failure.dto.response;

import java.util.List;
import java.util.Map;

public record FailureByCategoryResponse(Map<Long, List<FailureResponse>> response) {}
