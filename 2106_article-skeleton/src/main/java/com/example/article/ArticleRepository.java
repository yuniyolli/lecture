package com.example.article;

import com.example.article.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository
        extends JpaRepository<ArticleEntity, Long> {
    //메소드 잘 만들어주면 레포지토리가 쓸 준비를 해준다!!
    /*
    목표: articles 중 client의 조건에 따라 20개씩 나눠서 제공
    → 20, 21-40, 21-60
     */
    //id가 큰 순서대로 최상위 20개
    List<ArticleEntity> findTop20ByOrderByIdDesc();
    //id가 특정 값보다 작은 데이터 중 큰 순서대로 최상위 20개
    List<ArticleEntity> findTop20ByIdLessThanOrderByIdDesc(Long id);

    //제목에 쿼리가 들어가는 아티클 검사
    Page<ArticleEntity> findAllByTitleContains(String title, Pageable pageable);

}
