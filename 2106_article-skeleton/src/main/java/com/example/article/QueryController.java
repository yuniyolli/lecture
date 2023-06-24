package com.example.article;

import com.example.article.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
public class QueryController {
    //GET /path?query=keyword&limit=20
    //이런 요청을 받았을 떄, 쿼리에 할당되어있는 문자열과
    //리밋에 할당된 숫자를 받아와서 확인할 수 있으면 되는거야
    @GetMapping("/path")
    public Map<String, Object> queryParams(
            //map객체는 제이슨과 비슷. 앞쪽 개체로 키, 뒷쪽개체로 데이터! dto만들기귀찮아서!
            @RequestParam(value = "query", defaultValue = "hello") String query,
            @RequestParam(value = "limit",required = false) Integer limit
    ){
        log.info("query=" + query);
        log.info("limit=" + limit);

        Map<String,Object> response = new HashMap<>();
        response.put("query", query);
        response.put("limit", limit);
        return response;

    }

    @GetMapping("/search")
    public Page<ArticleDto> search(
            @RequestParam("query") String query, //검색기능 구현에 query는 필수! 검색어자나!
            @RequestParam(value = "page", defaultValue = "0")
            Integer pageNumber
    ) {
        return null;
    //return service.search(query, pageNumber);
    }
}
