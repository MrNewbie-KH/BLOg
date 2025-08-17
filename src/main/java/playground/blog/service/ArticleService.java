package playground.blog.service;

import playground.blog.dto.article.ArticleRequestDTO;
import playground.blog.dto.article.ArticleResponseDTO;
import playground.blog.dto.article.CardArticleResponseDTO;
import playground.blog.dto.article.CreateArticleRequestDTO;
import playground.blog.mapper.ArticleMapper;

import java.util.List;

public interface ArticleService {
    ArticleResponseDTO createArticle(CreateArticleRequestDTO requestDTO);
    List<CardArticleResponseDTO> findAllMyArticles();
    ArticleResponseDTO findArticleById(Long id);
    List<CardArticleResponseDTO>findArticlesByCategory(Long id);
}
