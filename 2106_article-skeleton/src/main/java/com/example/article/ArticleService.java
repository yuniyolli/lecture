package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    public ArticleDto createArticle(ArticleDto dto
    ) {
        ArticleEntity newArticle = new ArticleEntity();
        newArticle.setTitle(dto.getTitle());
        newArticle.setContent(dto.getContent());
        newArticle.setWriter(dto.getWriter());
        //newArticle = repository.save(newArticle); //this.repository해도 되
        //return ArticleDto.fromEntity(newArticle);
        return ArticleDto.fromEntity(repository.save(newArticle));
        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    public ArticleDto readArticle(Long id) {
        Optional<ArticleEntity> optionalArticle
                = repository.findById(id);
        //채워넣어보기
        //optional안에 Article이 들어있으면
        if (optionalArticle.isPresent()) {
            //DTO로 전환후 반환
            return ArticleDto.fromEntity(optionalArticle.get());
            //System.out.println(optionalArticle.get()); -> 땡
        }
        //아니면 404
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            //System.out.println(HttpStatus.BAD_REQUEST); -> 땡

        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);

    }

    public List<ArticleDto> readArticleAll() {
        List<ArticleDto> articleList = new ArrayList<>();
        for (ArticleEntity entity : this.repository.findAll()){
            articleList.add(ArticleDto.fromEntity(entity));
        }
        return articleList;
        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    public ArticleDto updateArticle(Long id, ArticleDto dto) {
        //ArticleEntity받아오기
        Optional<ArticleEntity> optionalArticle
                = repository.findById(id);
        //데이터 찾았을 때, ArticleEntity가 있을때
        if (optionalArticle.isPresent()) {
            //기존 객체 회수 이거 아니고
            // 전달받은 dto기준으로 수정
            ArticleEntity article = optionalArticle.get();
            article.setWriter(dto.getWriter());
            article.setTitle(dto.getTitle());
            article.setContent(dto.getContent());
            repository.save(article);
            return ArticleDto.fromEntity(article);
            //수정 데이터 적용 이거도 안필요한거
            //없다면 404

        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            //return throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    public void deleteArticle(Long id) {
        Optional<ArticleEntity> optionalArticle =
                this.repository.findById(id);
        //if it exist
        if (optionalArticle.isPresent()) {

            //student보고 혼자한건데 짧게쓸수있네? 몸이고생했다 오늘도...
           // ArticleEntity articleEntity =
            //        optionalArticle.get();
            //delete
           // this.repository.delete(articleEntity);

            repository.deleteById(id);

            /*
            마지막 방법. 이 아이디를 가진게 존재하냐? 존재하면 지우고 띵띵
            if (repository.existsById(id))
            repository.deleteById(id);
            else throw new Response...
             */
        }
        //or 404
        else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    //query
    /*
    public List<ArticleDto> readArticlePaged() {
        List<ArticleDto> articleDtoList =
                new ArrayList<>();
        //readall과 비슷하지만 findall대신 우리가 만든 메소드활용
        for (ArticleEntity entity:
                repository.findTop20ByOrderByIdDesc()) {
            articleDtoList.add(ArticleDto.fromEntity(entity));
        }
        return articleDtoList;
    }

     */



/*
    public List<ArticleDto> readArticlePaged() {
        //PageingAndSortingRepository 메소드에 전달하는 용도 pageable
        //조회하고 싶은 페이지의 정보를 담는 객체 생성
        //아래처럼 작성하면 20개씩 데이터를 나눌때 0번 페이지를 달라고 요청하는 Pageable
        //Pageable pageable = PageRequest.of(0, 20);
        Pageable pageable = PageRequest.of(0, 20, Sort.by("id").descending());
        Page<ArticleEntity> articleEntityPage
                = repository.findAll(pageable);

        //return articleEntityPage; //확인을 위해 일단 리턴갑 void대신 이걸로 지정해보자!


        List<ArticleDto> articleDtoList = new ArrayList<>(); //나중에 반환을 위해!!!
        for (ArticleEntity entity: articleEntityPage) {
            //articleEntity가 반복해서 반환 -> 그걸 다시 Dto로 바꾸어 반환할 수 있겠지?
            articleDtoList.add(ArticleDto.fromEntity(entity));
        }
        return articleDtoList;



    }



 */

//이제 최종목표!

    public Page<ArticleDto> readArticlePaged(
            Integer pageNumber, Integer pageSize
    ) {
        Pageable pageable = PageRequest.of(
        pageNumber, pageSize, Sort.by("id").descending());
        Page<ArticleEntity> articleEntityPage
                = repository.findAll(pageable);
        //stream, map method : 전달받은 함수를 각 원소에 인자로 전달한 결과물
        //다시 모아서 Stream으로
        //Page.map : 전달받은 함수를 각 원소에 인자로 전달한 결과를
        //다시 모아서 Page로
        Page<ArticleDto> articleDtoPage
                = articleEntityPage.map(ArticleDto::fromEntity);
        return articleDtoPage;
    }

    public Page<ArticleDto> search(
            String query, Integer pageNumber
    ) {
        Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("id").descending());
        return
        repository.findAllByTitleContains(query, pageable).map(ArticleDto::fromEntity);
    }





}
