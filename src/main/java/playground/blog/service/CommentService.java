package playground.blog.service;

import playground.blog.dto.comment.CommentRequestDTO;
import playground.blog.dto.comment.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    CommentResponseDTO createComment(CommentRequestDTO requestDTO);

    CommentResponseDTO updateComment(CommentRequestDTO requestDTO);

    void deleteComment(Long commentId);

    CommentResponseDTO getComment(Long commentId);

    List<CommentResponseDTO> getComments();
    List<CommentResponseDTO> getRepliesPerComment(Long commentId);

}
