package playground.blog.controller;

import org.springframework.web.bind.annotation.*;
import playground.blog.dto.tag.TagDto;
import playground.blog.service.TagService;

import java.util.List;

@RestController
public class CategoryController {


//        private final TagService categoryService;
//        @PostMapping
//        public TagDto createCategory(@RequestBody TagDto tagDto){
//            return tagService.createTag(tagDto.getName());
//        }
//        @GetMapping
//        public List<TagDto> getCategories(){
//            return tagService.getAllTags();
//        }
//        @GetMapping("/{id}")
//        public TagDto getCategoryById(@PathVariable Long id){
//            return tagService.getTagById(id);
//        }
//        @GetMapping("/search")
//        public TagDto getCategoryByName(@RequestParam String name){
//            return tagService.getTagByName(name);
//        }
//        @DeleteMapping("/{id}")
//        public void deleteCategory(@PathVariable Long id){
//            tagService.deleteTag(id);
//        }
//        @PutMapping("/{id}")
//        public TagDto updateCategory(@PathVariable Long id ,@RequestBody TagDto tagDto){
//            return tagService.updateTag(id,tagDto);
//        }
}
