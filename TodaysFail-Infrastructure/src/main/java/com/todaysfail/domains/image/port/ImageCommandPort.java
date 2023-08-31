package com.todaysfail.domains.image.port;

import com.todaysfail.domains.image.entity.ImageEntity;

public interface ImageCommandPort {
    ImageEntity save(ImageEntity imageEntity);
}
