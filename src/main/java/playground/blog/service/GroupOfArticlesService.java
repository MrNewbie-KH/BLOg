package playground.blog.service;

import playground.blog.dto.groupOfArticles.GroupOfArticlesRequestDTO;
import playground.blog.dto.groupOfArticles.GroupOfArticlesResponseDTO;

import java.util.List;

public interface GroupOfArticlesService {
    public GroupOfArticlesResponseDTO createGroup(GroupOfArticlesRequestDTO groupOfArticlesRequestDTO);
    public List<GroupOfArticlesResponseDTO> getAllMyGroups(Long userId);
}
