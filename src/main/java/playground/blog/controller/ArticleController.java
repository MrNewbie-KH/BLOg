package playground.blog.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;
import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.dto.article.CardArticleResponseDTO;
import playground.blog.dto.article.CreateArticleRequestDTO;
import playground.blog.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/articles")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    @PostMapping
    public ArticleResponseDTO createArticle(@RequestBody CreateArticleRequestDTO requestDTO) {
        return articleService.createArticle(requestDTO);
    }
    @GetMapping
    public List<CardArticleResponseDTO> findAllMyArticles() {
        return articleService.findAllMyArticles();
    }
    @GetMapping("/{id}")
    public ArticleResponseDTO findArticleById(@PathVariable Long id) {
        return  articleService.findArticleById( id);
    }
    @GetMapping("/category/{id}")
    public List<CardArticleResponseDTO> findArticlesByCategory(@PathVariable Long id) {
        return  articleService.findArticlesByCategory(id);
    }
    @GetMapping("/author/{id}")
    public List<CardArticleResponseDTO> findArticlesByAuthorId (@PathVariable Long id) {
        return  articleService.findArticlesByAuthorId(id);
    }
    @PostMapping("/{id}/categories")
    public ArticleResponseDTO addCategoriesToArticle(@PathVariable Long id,@RequestBody List<Long> categoriesId) {
        return  articleService.addCategoryToArticle(id,categoriesId);
    }


/*

t

update article
delete article
add category to an article
add tag to an article
get all articles based on  a specific tag
sort article based on the available like count
sorting and pagination
 */
}
