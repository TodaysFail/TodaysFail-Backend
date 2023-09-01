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
import com.todaysfail.domains.tag.port.TagCommandPort;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.exception.UserNotFountException;
import com.todaysfail.domains.user.port.UserQueryPort;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FailureRegisterService implements FailureRegisterUseCase {
    private final FailureCommandPort failureCommandPort;
    private final CategoryQueryPort categoryQueryPort;
    private final CategoryColorQueryPort categoryColorQueryPort;
    private final TagCommandPort tagCommandPort;
    private final UserQueryPort userQueryPort;

    @Override
    public Failure execute(Command command) {
        validateFailureDate(command.date());
        validateFailureTagSize(command.tagSet());
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

        Set<TagEntity> tagEntities = tagCommandPort.saveAndRetrieveAllTags(command.tagSet());
        Set<Tag> tagSet = tagEntities.stream().map(Tag::from).collect(Collectors.toSet());

        category.validateOwnership(command.userId());
        FailureEntity failureEntity =
                failureCommandPort.registerFailure(
                        command.userId(),
                        command.categoryId(),
                        command.date(),
                        command.title(),
                        command.content(),
                        command.impression(),
                        command.tagSet());
        return Failure.registerFailure(failureEntity, user, category, tagSet);
    }

    private void validateFailureTagSize(Set<String> tagSet) {
        /** 태그는 최대 3개까지만 등록 가능하다. */
        if (tagSet.size() > 3) {
            throw TagCountExceedException.EXCEPTION;
        }
    }

    private void validateFailureDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw FutureFailureDateException.EXCEPTION;
        }
    }
}
