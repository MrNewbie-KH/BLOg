package playground.blog.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import playground.blog.dto.like.LikeResponseDTO;
import playground.blog.entity.Like;
import playground.blog.entity.User;
import playground.blog.exception.custom.NotFoundException;

import playground.blog.mapper.LikeMapper;
import playground.blog.repository.ArticleRepository;
import playground.blog.repository.LikeRepository;
import playground.blog.repository.UserRepository;
import playground.blog.service.LikeService;
import playground.blog.service.UserService;

import java.util.Optional;

@Service
@Data
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final ArticleRepository articleRepository;
    private final LikeMapper likeMapper;

    @Override
    public LikeResponseDTO toggleLike(Long id) {
        Authentication authObject = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authObject.getName();
//         check with both article and user id
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new NotFoundException("User not found exception"));
      Optional<Like> like=  likeRepository.findByUserIdAndArticleId(user.getId(),id);
      if(like.isPresent()){
          likeRepository.deleteById(like.get().getId());
      }else{
          Like likeToBeCreated = new Like();
          likeToBeCreated.setUser(user);
          likeToBeCreated.setArticle(articleRepository.getReferenceById(id));
          likeRepository.save(likeToBeCreated);
      }
      Long countNumberOfLikes = likeRepository.countByArticleId(id);


        return likeMapper.toLikeResponseDTO(id, !like.isPresent(),countNumberOfLikes);
    }
}
