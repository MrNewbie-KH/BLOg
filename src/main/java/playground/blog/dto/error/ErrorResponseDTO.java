package playground.blog.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDTO {
    private String message;
    private HttpStatus status;
    private String path;
    private String  error;
}
