package com.todaysfail.api.web.category.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record CategoryColorModifyRequest(
        @NotNull(message = "색상 코드는 필수입니다.")
                @Pattern(
                        regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
                        message = "색상 코드는 Hex 코드 형식이어야 합니다.")
                String colorCode,
        @NotNull(message = "색상 이름은 필수입니다.") String colorName) {}
