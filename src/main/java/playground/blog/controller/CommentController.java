package playground.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import playground.blog.dto.comment.CommentRequestDTO;
import playground.blog.dto.comment.CommentResponseDTO;
import playground.blog.service.CommentService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentResponseDTO createComment(@RequestBody CommentRequestDTO requestDTO) {
        return commentService.createComment(requestDTO);
    }
    @GetMapping("/article/{id}")
    public List<CommentResponseDTO> getCommentsPerArticle (@PathVariable Long id) {
        return commentService.getCommentsPerArticle(id);

    }
    @GetMapping("/{id}")
    public CommentResponseDTO getCommentById(@PathVariable Long id){
        return commentService.getComment(id);
    }
    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable Long id){
        commentService.deleteComment(id);
    }


//    update comment / reply
//    delete comment / reply
//    get all comments
//    get all replies per comment
//    get single comment
//    get single comment to be shown by the user who is logged
}
