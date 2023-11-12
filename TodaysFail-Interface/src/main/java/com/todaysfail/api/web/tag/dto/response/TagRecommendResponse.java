package com.todaysfail.api.web.tag.dto.response;

import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

public record TagRecommendResponse(String tagName, double score) {
    public static TagRecommendResponse from(TypedTuple<String> tuple) {
        return new TagRecommendResponse(tuple.getValue(), tuple.getScore());
    }
}
