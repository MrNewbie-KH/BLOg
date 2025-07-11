package playground.blog.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
//    request may need to update or delete
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}