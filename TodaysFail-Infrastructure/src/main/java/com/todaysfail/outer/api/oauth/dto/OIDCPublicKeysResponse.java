package com.todaysfail.outer.api.oauth.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OIDCPublicKeysResponse {
    private List<OIDCPublicKeyDto> keys;
}
