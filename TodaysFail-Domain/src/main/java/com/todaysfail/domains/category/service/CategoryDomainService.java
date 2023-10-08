package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.port.CategoryCommandPort;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDomainService {
    private final CategoryCommandPort categoryCommandPort;
    private final UserQueryPort userQueryPort;

    public Category register(Category category) {
        return categoryCommandPort.save(category);
    }

    public Category modify(
            Long userId, Long categoryId, String categoryName, CategoryColor categoryColor) {
        User user = userQueryPort.queryUser(userId);
        Category category = categoryCommandPort.queryCategory(categoryId);
        category.validateOwnership(user.getId());
        category.modify(categoryName, categoryColor);
        return category;
    }

    public void delete(Long categoryId, Long userId) {
        User user = userQueryPort.queryUser(userId);
        Category category = categoryCommandPort.queryCategory(categoryId);
        category.validateOwnership(user.getId());
        categoryCommandPort.delete(category);
    }
}
