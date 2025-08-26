package playground.blog.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import playground.blog.dto.comment.CommentRequestDTO;
import playground.blog.dto.comment.CommentResponseDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.Article;
import playground.blog.entity.Comment;
import playground.blog.entity.User;
import playground.blog.exception.custom.NotFoundException;
import playground.blog.mapper.CommentMapper;
import playground.blog.mapper.UserMapper;
import playground.blog.repository.ArticleRepository;
import playground.blog.repository.CommentRepository;
import playground.blog.repository.UserRepository;
import playground.blog.service.CommentService;

import java.util.List;

@Slf4j
@Service
@Data
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    @Override
    public CommentResponseDTO createComment(CommentRequestDTO requestDTO) {
        log.info("parentCommentId = {}", requestDTO.getParentCommentId());

        boolean isComment = false;
        Comment parentComment = null;
//        check for the article existance

        Article article = articleRepository.findById(requestDTO.getArticleId())
                .orElseThrow(() -> new NotFoundException("Article not found"));

//        in case of reply check for the parent id existance or not
        if (requestDTO.getParentCommentId() == null) {
            isComment = true;
        } else {

            parentComment = commentRepository.findById(requestDTO.getParentCommentId()).orElseThrow(() -> new NotFoundException("Parent Comment not found"));
            if (!parentComment.isComment()) {
                parentComment = parentComment.getParentComment();
            }
            if (!parentComment.getArticle().getId().equals(requestDTO.getArticleId())) {
                throw new RuntimeException("Parent comment must be related to the same article");
            }
        }
        Authentication authObject = SecurityContextHolder.getContext().getAuthentication();
        String username = authObject.getName();
        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        UserResponseDTO userResponseDTO = userMapper.toResponse(user);

        Comment newComment = commentMapper.toEntity(requestDTO, article, isComment, user,parentComment);
        commentRepository.save(newComment);

        return commentMapper.toResponseDTO(newComment, userResponseDTO, 0L);
    }

    @Override
    public CommentResponseDTO updateComment(CommentRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public CommentResponseDTO getComment(Long commentId) {
        return null;
    }

    @Override
    public List<CommentResponseDTO> getComments() {
        return List.of();
    }

    @Override
    public List<CommentResponseDTO> getRepliesPerComment(Long commentId) {
        return List.of();
    }
}
