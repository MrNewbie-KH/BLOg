package playground.blog.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import playground.blog.dto.groupOfArticles.GroupOfArticlesRequestDTO;
import playground.blog.dto.groupOfArticles.GroupOfArticlesResponseDTO;
import playground.blog.entity.GroupOfArticles;
import playground.blog.service.GroupOfArticlesService;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@Data
@AllArgsConstructor
public class GroupOfArticlesController {
    private final GroupOfArticlesService groupOfArticlesService;
    //    create group
    @PostMapping
    public GroupOfArticlesResponseDTO createGroup(@RequestBody GroupOfArticlesRequestDTO groupOfArticlesRequestDTO) {
        return groupOfArticlesService.createGroup(groupOfArticlesRequestDTO);
    }
//    retrieve all groups
    @GetMapping("/user/{id}")
    public List<GroupOfArticlesResponseDTO> getAllMyGroups(@PathVariable Long id) {
        return groupOfArticlesService.getAllMyGroups( id);
    }
//    retrieve group by id
//    retrieve group by title
//    update group
//    delete group
//    add post to group
//    remove post from group
}

