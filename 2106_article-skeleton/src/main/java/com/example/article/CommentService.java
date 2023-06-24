package com.example.article;

import com.example.article.dto.CommentDto;
import com.example.article.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public CommentDto createComment(Long articleId, CommentDto dto) {
        //articleId를 Id로 가진 ArticleEntity가 존재하는지 ?
        //이걸 판단하고싶음
        //commentRepository 만으로는 부족!
        if (!articleRepository.existsById(articleId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        //자유롭게 상황대처
        CommentEntity newComment = new CommentEntity();
        newComment.setWriter(dto.getWriter());
        newComment.setContent(dto.getContent());
        newComment.setArticleId(articleId);
        newComment = commentRepository.save(newComment);
        return CommentDto.fromEntity(newComment);
    }

    //게시글 댓글 전체 조회
    //이름, 반환타입, 인자 -> 전체 조회...! 리스트!!
    public List<CommentDto> readCommentAll(Long articleId) {
        List<CommentEntity> commentEntities =
                commentRepository.findAllByArticleId(articleId);
        List<CommentDto> commentList = new ArrayList<>();
        for (CommentEntity entity: commentEntities) {
            commentList.add(CommentDto.fromEntity(entity));
        }

        return commentList;
    }

    //게시글 댓글 수정
    //수정하고자 하는 댓글이 지정한 게시글에 있는지 확인할 목적으로
    //articleId도 첨부한다. -> 기능상으로는 COMMENTID만있어도 되
    public CommentDto updateComment(
            Long articleId,
            Long commentId,
            CommentDto dto )//수정을 하고있으니까 실제로 수정하고자하는 대상에 대한 정보가 담긴 dto필요
    {
        //요청한 댓글이 존재하는지
        Optional<CommentEntity> optionalComment
                = commentRepository.findById(commentId);
        //존재하지 않으면 예외 발생
        if (optionalComment.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        //아니면 로직 진행
        CommentEntity comment = optionalComment.get();
        //대상 댓글이 대상 게시글의 댓글이 맞는지
        if (!articleId.equals(comment.getArticleId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        //요청 게시글, 댓글 아이디가 같은 자원으로서 그 게시글이 댓글의 자원이 아닌데도
        //수정을 한다는건 해석불가능한 bad request (400 error)

        comment.setContent(dto.getContent());
        comment.setWriter(dto.getWriter());

        return CommentDto.fromEntity(commentRepository.save(comment));
    }

    //게시글 댓글 삭제
//public CommentDto deleteComment ( //굳이 반환 안해도 된다고 딜리트는 ㅜㅜ 보이드라고.. 매번이러누
    public void deleteComment(
        Long articleId,
        Long commentId
        //CommentDto dto 이거 안필요하다고오오
) {
        //요청한 댓글 존재하는지
        Optional<CommentEntity> optionalComment
                = commentRepository.findById(commentId);
        //존재하지 않으면 예외 발생
    if (optionalComment.isEmpty())
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    //아니면 로직 진행
    CommentEntity comment = optionalComment.get();
    //댓글이 대상 게시글의 댓글이 맞는지
    if (!articleId.equals(comment.getArticleId()))
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    //맞으면 삭제! -> 이거도 return 필요없어!!!!!!!!! 힝구ㅜㅜㅜㅜ
    //return CommentDto.fromEntity(commentRepository.delete(comment));
    commentRepository.delete(comment);
}

}
