package playground.blog.exception.global;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import playground.blog.dto.error.ErrorResponseDTO;
import playground.blog.exception.custom.EntityAlreadyExist;
import playground.blog.exception.custom.NotFoundException;
import playground.blog.exception.custom.NotNullException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .path(request.getRequestURI())
                .error("NOT FOUND")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleEntityAlreadyExistException(EntityAlreadyExist ex, HttpServletRequest request) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .path(request.getRequestURI())
                .error("BAD REQUEST")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponseDTO> handleNotNullException(NotNullException ex, HttpServletRequest request) {
        ErrorResponseDTO error = ErrorResponseDTO.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .path(request.getRequestURI())
                .error("BAD REQUEST")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

    }
}
