package playground.blog.dto.comment;

import playground.blog.dto.user.UserResponseDTO;

import java.sql.Timestamp;

public class CommentResponseDTO {
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private UserResponseDTO user;
    private Long numberOfReplies;
}
