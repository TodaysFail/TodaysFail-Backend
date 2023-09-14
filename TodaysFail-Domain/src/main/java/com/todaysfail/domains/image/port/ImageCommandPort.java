package com.todaysfail.domains.image.port;

import com.todaysfail.domains.image.domain.Image;

public interface ImageCommandPort {
    Image save(Image imageEntity);
}
