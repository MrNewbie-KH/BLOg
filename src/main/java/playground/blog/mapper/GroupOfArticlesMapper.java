package playground.blog.mapper;

import playground.blog.dto.groupOfArticles.GroupOfArticlesRequestDTO;
import playground.blog.dto.groupOfArticles.GroupOfArticlesResponseDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.GroupOfArticles;
import playground.blog.entity.User;

import java.util.List;

public interface GroupOfArticlesMapper {
    GroupOfArticles toEntity(User user , GroupOfArticlesRequestDTO groupOfArticlesRequestDTO);
    GroupOfArticlesResponseDTO toResponse(UserResponseDTO userResponseDTO  ,GroupOfArticles groupOfArticles);
    List<GroupOfArticlesResponseDTO>listToResponse(UserResponseDTO userResponseDTO,List<GroupOfArticles> groupOfArticlesList);
}
