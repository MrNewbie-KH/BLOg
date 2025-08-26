package playground.blog.mapper.implementation;

import org.springframework.stereotype.Component;
import playground.blog.dto.comment.CommentRequestDTO;
import playground.blog.dto.comment.CommentResponseDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.Article;
import playground.blog.entity.Comment;
import playground.blog.entity.User;
import playground.blog.mapper.CommentMapper;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public CommentResponseDTO toResponseDTO(Comment comment, UserResponseDTO user, Long numberOfReplies) {
        return CommentResponseDTO.builder()
                .user(user)
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .numberOfReplies(numberOfReplies)
                .isComment(comment.isComment())
                .build();
    }

    @Override
    public Comment toEntity(CommentRequestDTO commentRequestDTO, Article article, boolean isComment, User user,Comment parentComment) {

        return Comment.builder()
                .content(commentRequestDTO.getContent())
                .isComment(isComment)
                .user(user)
                .article(article)
                .build();
    }
}
