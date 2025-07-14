package playground.blog.mapper.implementation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;
import playground.blog.dto.groupOfArticles.GroupOfArticlesRequestDTO;
import playground.blog.dto.groupOfArticles.GroupOfArticlesResponseDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.GroupOfArticles;
import playground.blog.entity.User;
import playground.blog.mapper.GroupOfArticlesMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Builder
@Component
public class GroupOfArticlesMapperImpl implements GroupOfArticlesMapper {
@Override
    public GroupOfArticles toEntity(User user, GroupOfArticlesRequestDTO groupOfArticlesRequestDTO) {
        return  GroupOfArticles.builder()
                .title(groupOfArticlesRequestDTO.getTitle())
                .description(groupOfArticlesRequestDTO.getDescription())
                .user(user)
                .build();
    }

    @Override
    public GroupOfArticlesResponseDTO toResponse(UserResponseDTO userResponseDTO, GroupOfArticles groupOfArticles) {
      return   GroupOfArticlesResponseDTO.builder()
                .title(groupOfArticles.getTitle())
                .description(groupOfArticles.getDescription())
                .id(groupOfArticles.getId())
                .user(userResponseDTO)
                .build();
    }
    @Override
    public List<GroupOfArticlesResponseDTO> listToResponse(UserResponseDTO userResponseDTO, List<GroupOfArticles> groupOfArticlesList){
     return  groupOfArticlesList.stream().map(groupOfArticles ->
             toResponse(userResponseDTO,groupOfArticles)).collect(Collectors.toList());
    };

}
