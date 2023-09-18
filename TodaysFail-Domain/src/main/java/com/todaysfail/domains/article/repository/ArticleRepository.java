package com.todaysfail.domains.article.repository;

import com.todaysfail.domains.article.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {}
