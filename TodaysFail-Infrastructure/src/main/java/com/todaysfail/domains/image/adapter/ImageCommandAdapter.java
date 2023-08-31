package com.todaysfail.domains.image.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.image.entity.ImageEntity;
import com.todaysfail.domains.image.port.ImageCommandPort;
import com.todaysfail.domains.image.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class ImageCommandAdapter implements ImageCommandPort {
    private final ImageRepository imageRepository;

    @Override
    public ImageEntity save(ImageEntity imageEntity) {
        return imageRepository.save(imageEntity);
    }
}
