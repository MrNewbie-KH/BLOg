package playground.blog.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import playground.blog.dto.user.UserResponseDTO;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    private String content;
    private Long commentId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean isComment;
    private UserResponseDTO user;
    private Long numberOfReplies;
}
