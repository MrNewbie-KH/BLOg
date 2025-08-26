package playground.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import playground.blog.dto.comment.CommentRequestDTO;
import playground.blog.dto.comment.CommentResponseDTO;
import playground.blog.service.CommentService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentResponseDTO createComment(@RequestBody CommentRequestDTO requestDTO) {
        return commentService.createComment(requestDTO);
    }
//    create comment
//    create reply
//    update comment / reply
//    delete comment / reply
//    get all comments
//    get all replies per comment
//    get single comment
//    get single comment to be shown by the user who is logged
}
