package playground.blog.service;

import playground.blog.dto.tag.TagDto;

import java.util.List;

public interface TagService {
     List<TagDto> getAllTags();
    TagDto getTagById(Long id);
    TagDto getTagByName(String name);
    TagDto createTag(String name);
    TagDto updateTag(Long id,TagDto tagDto);
    void deleteTag(Long id);

}