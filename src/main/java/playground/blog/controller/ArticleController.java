package playground.blog.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.service.ArticleService;

@RestController
@RequestMapping(path = "/api/articles")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping
    public ArticleResponseDTO createArticle(@RequestBody ArticleRequestDTO articleRequestDTO) {
        return articleService.createArticle(articleRequestDTO);

    }
/*
create article
get all articles
get article by id
get articles written by an author
update article
delete article
add category to an article
get all articles of a specific category
add tag to an article
get all articles based on  a specific tag
sort article based on the available like count
sorting and pagination
 */

}
