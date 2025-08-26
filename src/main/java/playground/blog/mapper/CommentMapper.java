package playground.blog.mapper;

import playground.blog.dto.comment.CommentRequestDTO;
import playground.blog.dto.comment.CommentResponseDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.Article;
import playground.blog.entity.Comment;
import playground.blog.entity.User;

public interface CommentMapper {
    CommentResponseDTO toResponseDTO(Comment comment, UserResponseDTO user, Long numberOfReplies);

    Comment toEntity(CommentRequestDTO commentRequestDTO, Article article, boolean isComment, User user,Comment parentComment);
}
