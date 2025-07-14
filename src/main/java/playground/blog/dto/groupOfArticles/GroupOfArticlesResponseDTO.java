package playground.blog.dto.groupOfArticles;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import playground.blog.dto.user.UserResponseDTO;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupOfArticlesResponseDTO {
    Long id;
    String title;
    String description;
    UserResponseDTO user;
}
