package com.todaysfail.api.web.tag.usecase;

import static com.todaysfail.common.consts.TodaysFailConst.RECOMMEND_TAG_KEY;

import com.todaysfail.api.web.tag.dto.response.TagRecommendResponse;
import com.todaysfail.common.annotation.UseCase;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

@UseCase
@RequiredArgsConstructor
public class TagRecommendUseCase {
    private final RedisTemplate<String, String> redisTemplate;

    public List<TagRecommendResponse> execute() {
        String key = RECOMMEND_TAG_KEY;
        ZSetOperations<String, String> ZSetOperations = redisTemplate.opsForZSet();
        Set<TypedTuple<String>> typedTuples = ZSetOperations.reverseRangeWithScores(key, 0, 9);
        return typedTuples.stream().map(TagRecommendResponse::from).collect(Collectors.toList());
    }
}
