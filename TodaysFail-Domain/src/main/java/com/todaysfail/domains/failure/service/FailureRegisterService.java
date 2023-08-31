package com.todaysfail.domains.failure.service;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.entity.CategoryEntity;
import com.todaysfail.domains.category.exception.CategoryColorNotFoundException;
import com.todaysfail.domains.category.exception.CategoryNotFoundException;
import com.todaysfail.domains.category.port.CategoryColorQueryPort;
import com.todaysfail.domains.category.port.CategoryQueryPort;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.failure.entity.FailureEntity;
import com.todaysfail.domains.failure.exception.FutureFailureDateException;
import com.todaysfail.domains.failure.port.FailureCommandPort;
import com.todaysfail.domains.failure.usecase.FailureRegisterUseCase;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.tag.entity.TagEntity;
import com.todaysfail.domains.tag.exception.TagCountExceedException;
import com.todaysfail.domains.tag.exception.TagNotFoundException;
import com.todaysfail.domains.tag.port.TagQueryPort;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.exception.UserNotFountException;
import com.todaysfail.domains.user.port.UserQueryPort;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FailureRegisterService implements FailureRegisterUseCase {
    private final FailureCommandPort failureCommandPort;
    private final CategoryQueryPort categoryQueryPort;
    private final CategoryColorQueryPort categoryColorQueryPort;
    private final TagQueryPort tagQueryPort;
    private final UserQueryPort userQueryPort;

    @Override
    public Failure execute(Command command) {
        User user =
                User.from(
                        userQueryPort
                                .queryUser(command.userId())
                                .orElseThrow(() -> UserNotFountException.EXCEPTION));
        CategoryEntity categoryEntity =
                categoryQueryPort
                        .queryCategory(command.categoryId())
                        .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
        CategoryColorEntity categoryColorEntity =
                categoryColorQueryPort
                        .queryCategoryColor(categoryEntity.getCategoryColorId())
                        .orElseThrow(() -> CategoryColorNotFoundException.EXCEPTION);
        Category category = Category.of(categoryEntity, categoryColorEntity);
        List<TagEntity> tagEntities = tagQueryPort.queryTagList(command.tagIdList());
        List<Tag> tagList = tagEntities.stream().map(Tag::from).collect(Collectors.toList());
        category.validateOwnership(command.userId());
        validateFailureDate(command.date());
        validateFailureTagSize(command.tagIdList(), tagEntities);
        FailureEntity failureEntity =
                failureCommandPort.registerFailure(
                        command.userId(),
                        command.categoryId(),
                        command.date(),
                        command.title(),
                        command.content(),
                        command.impression(),
                        command.tagIdList());
        return Failure.registerFailure(failureEntity, user, category, tagList);
    }

    private void validateFailureTagSize(List<Long> tagIdList, List<TagEntity> tagEntities) {
        /** 태그는 최대 3개까지만 등록 가능하다. */
        if (tagIdList.size() > 3) {
            throw TagCountExceedException.EXCEPTION;
        }
        /** 태그가 존재하지 않는 경우 예외를 발생시킨다. */
        if (tagEntities.size() != tagIdList.size()) {
            throw TagNotFoundException.EXCEPTION;
        }
    }

    private void validateFailureDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw FutureFailureDateException.EXCEPTION;
        }
    }
}
