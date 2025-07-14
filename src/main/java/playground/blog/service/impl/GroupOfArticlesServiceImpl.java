package playground.blog.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import playground.blog.dto.groupOfArticles.GroupOfArticlesRequestDTO;
import playground.blog.dto.groupOfArticles.GroupOfArticlesResponseDTO;
import playground.blog.dto.user.UserResponseDTO;
import playground.blog.entity.GroupOfArticles;
import playground.blog.entity.User;
import playground.blog.mapper.GroupOfArticlesMapper;
import playground.blog.mapper.UserMapper;
import playground.blog.repository.GroupOfArticlesRepository;
import playground.blog.repository.UserRepository;
import playground.blog.service.GroupOfArticlesService;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class GroupOfArticlesServiceImpl implements GroupOfArticlesService {
    private final GroupOfArticlesRepository groupOfArticlesRepository;
    private final UserRepository userRepository;
    private final GroupOfArticlesMapper groupOfArticlesMapper;
    private final UserMapper userMapper;

    @Override
    public GroupOfArticlesResponseDTO createGroup(GroupOfArticlesRequestDTO groupOfArticlesRequestDTO) {
        User user = userRepository.findById(groupOfArticlesRequestDTO.getUserId()).orElse(null);

       GroupOfArticles newGroup=  groupOfArticlesRepository.save(groupOfArticlesMapper.toEntity(user,groupOfArticlesRequestDTO));
        UserResponseDTO userResponseDTO = userMapper.toResponse(userRepository.findById(groupOfArticlesRequestDTO.getUserId()).orElse(null));
    return  groupOfArticlesMapper.toResponse(userResponseDTO,newGroup);
    }
    @Override
    public List<GroupOfArticlesResponseDTO> getAllMyGroups(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        UserResponseDTO userResponseDTO = userMapper.toResponse(user);
      List<GroupOfArticles>allGroupsByUser=   groupOfArticlesRepository.findByUser(user);
      return groupOfArticlesMapper.listToResponse(userResponseDTO,allGroupsByUser);
    }
}
