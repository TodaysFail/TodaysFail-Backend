package com.todaysfail.api.web.image;

import com.todaysfail.api.web.image.dto.response.ImageResponse;
import com.todaysfail.api.web.image.dto.response.PresignedUrlResponse;
import com.todaysfail.api.web.image.mapper.ImageMapper;
import com.todaysfail.common.type.image.ImageFileExtension;
import com.todaysfail.common.type.image.ImageType;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.image.usecase.GeneratePresignedUrlUseCase;
import com.todaysfail.domains.image.usecase.ImageUploadSuccessUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "3. [이미지]")
@RestController
@RequestMapping("/api/v1/images")
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
public class ImageController {
    private final ImageMapper imageMapper;
    private final GeneratePresignedUrlUseCase generatePresignedUrlUseCase;
    private final ImageUploadSuccessUseCase imageUploadSuccessUseCase;

    @Operation(summary = "이미지 업로드를 위한 presigned url을 생성합니다.")
    @GetMapping("/presigned-url")
    public PresignedUrlResponse generatePresignedUrl(
            @Parameter(description = "이미지 타입", required = true) @RequestParam ImageType imageType,
            @Parameter(description = "이미지 파일의 확장자", required = true) @RequestParam
                    ImageFileExtension imageFileExtension) {
        Long userId = SecurityUtils.getCurrentUserId();
        return imageMapper.toPresignedUrlResponse(
                generatePresignedUrlUseCase.execute(userId, imageType, imageFileExtension));
    }

    @Operation(summary = "이미지 업로드 성공 시 호출합니다.")
    @GetMapping("/presigned-url/success")
    public ImageResponse successGeneratePresignedUrl(
            @Parameter(description = "이미지 타입", required = true) @RequestParam ImageType imageType,
            @Parameter(description = "이미지 파일의 확장자", required = true) @RequestParam
                    ImageFileExtension imageFileExtension,
            @Parameter(description = "이미지 파일의 키", required = true) @RequestParam String imageKey) {
        Long userId = SecurityUtils.getCurrentUserId();
        String imageUrl =
                imageUploadSuccessUseCase.success(userId, imageType, imageFileExtension, imageKey);
        return imageMapper.toImageResponse(imageUrl);
    }
}
