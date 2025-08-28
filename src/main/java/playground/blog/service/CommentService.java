package playground.blog.service;

import playground.blog.dto.comment.CommentRequestDTO;
import playground.blog.dto.comment.CommentResponseDTO;
import playground.blog.dto.comment.CommentUpdateRequestDTO;

import java.util.List;

public interface CommentService {

    CommentResponseDTO createComment(CommentRequestDTO requestDTO);

    CommentResponseDTO updateComment(Long commentId,CommentUpdateRequestDTO requestDTO);

    void deleteComment(Long commentId);

    CommentResponseDTO getComment(Long commentId);

    List<CommentResponseDTO> getCommentsPerArticle(Long articleId);
    List<CommentResponseDTO> getRepliesPerComment(Long commentId);
    
}
