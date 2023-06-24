package com.example.article;

import com.example.article.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<CommentEntity, Long> {
    //CommentEntity중 articleId가 id인 CommetnEntity만 반환하는 메소드
    List<CommentEntity> findAllByArticleId(Long id);

}
