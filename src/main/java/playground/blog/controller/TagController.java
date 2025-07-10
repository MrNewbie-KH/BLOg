package playground.blog.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import playground.blog.dto.tag.TagDto;
import playground.blog.service.TagService;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    @PostMapping
    public TagDto createTag(@RequestBody TagDto tagDto){
        return tagService.createTag(tagDto.getName());
    }
    @GetMapping
    public List<TagDto> getTags(){
        return tagService.getAllTags();
    }
    @GetMapping("/{id}")
    public TagDto getTagById(@PathVariable Long id){
        return tagService.getTagById(id);
    }
    @GetMapping("/search")
    public TagDto getTagByName(@RequestParam String name){
        return tagService.getTagByName(name);
    }
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
    }
    @PutMapping("/{id}")
    public TagDto updateTag(@PathVariable Long id ,@RequestBody TagDto tagDto){
        return tagService.updateTag(id,tagDto);
    }
}
