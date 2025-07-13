package playground.blog.service;

import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.mapper.ArticleMapper;

import java.util.List;

public interface ArticleService {
    ArticleResponseDTO createArticle(ArticleRequestDTO articleRequestDTO);
//    List<ArticleResponseDTO> getAllArticles();
}
