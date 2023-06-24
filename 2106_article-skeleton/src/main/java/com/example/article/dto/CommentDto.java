package com.example.article.dto;

import com.example.article.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String writer;
    private String content;
    private Long articleId;

    public static CommentDto fromEntity(CommentEntity entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setArticleId(entity.getArticleId());
        dto.setWriter(entity.getWriter());
        dto.setContent(entity.getContent());
        return dto;
    }
}
