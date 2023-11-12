package com.todaysfail.api.web.tag;

import com.todaysfail.api.web.tag.dto.response.TagResponse;
import com.todaysfail.api.web.tag.usecase.TagPopularUseCase;
import com.todaysfail.api.web.tag.usecase.TagSearchUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "7. [태그]")
@RestController
@RequestMapping("/api/v1/tags")
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
public class TagController {
    private final TagSearchUseCase tagSearchUseCase;
    private final TagPopularUseCase tagPopularUseCase;

    @Operation(summary = "태그를 검색합니다. (5개)")
    @GetMapping("/search")
    public List<TagResponse> search(@RequestParam String searchKeyword) {
        return tagSearchUseCase.execute(searchKeyword);
    }

    @Operation(summary = "많이 사용된 태그를 조회합니다. (5개)")
    @GetMapping("/popular")
    public List<TagResponse> popular() {
        return tagPopularUseCase.execute();
    }

    // @Operation(summary = "추천 태그를 조회합니다.")
    // @GetMapping("/recommend")
    // TODO: 추천 태그 조회 API 구현
}
