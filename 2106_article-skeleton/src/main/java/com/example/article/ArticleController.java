package com.example.article;

import com.example.article.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
// 어노테이션 붙이기
@RestController //@ResponseBody 생략가능
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService service;

    // POST /articles
    @PostMapping
    //RESTful 한 API는 행동의 결과로 반영된 자원의 상태를 반환함이 옳다
    public ArticleDto create(@RequestBody ArticleDto dto) {
        return service.createArticle(dto);
    }


    // GET /articles
    /*
    @GetMapping
    public List<ArticleDto> readAll() {
        return service.readArticleAll();
    }

     */
    @GetMapping
    public Page<ArticleDto> readAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit
    ) {
        //return null;
        return service.readArticlePaged(page, limit);
    }


    // GET /articles/{id}
    @GetMapping("/{id}")
    public ArticleDto read(@PathVariable("id") Long id) {
        return service.readArticle(id);
    }

    // PUT /articles/{id}
    @PutMapping("/{id}")
    public ArticleDto update(@PathVariable("id") Long id, //url의 id
                             @RequestBody ArticleDto dto)  //HTTP Request Body
    {
        return service.updateArticle(id, dto);
    }

    // DELETE /articles/{id}
    @DeleteMapping("/{id}")
    //삭제: 반환값이 없음
        public void delete(
            @PathVariable("id") Long id
    ) {
        //void -> no retrun value]
        // return service.deleteArticle(id);
    }

    //query
    //GET /articles/page-test
    @GetMapping("/page-test")
    public List<ArticleDto> readPageTest() {
        return service.readArticleAll();
    }

}
