package playground.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import playground.blog.dto.like.LikeRequestDTO;
import playground.blog.dto.like.LikeResponseDTO;
import playground.blog.service.impl.LikeServiceImpl;

@RestController
@RequestMapping("/api/v1/likes")
@AllArgsConstructor
public class LikeController {
    private final LikeServiceImpl likeServiceImpl;


    @PostMapping()
    public LikeResponseDTO toggleLike (@RequestBody LikeRequestDTO likeRequestDTO){
      return   likeServiceImpl.toggleLike(likeRequestDTO.getArticleId());
    }
}
