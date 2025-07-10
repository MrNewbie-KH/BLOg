package playground.blog.service;

import playground.blog.dto.tag.TagDto;

import java.util.List;

public interface TagService {
    public List<TagDto> getAllTags();
//    TagDto getTagById(Long id);
//    TagDto getTagByName(String name);
    public TagDto createTag(String name);
//    TagDto updateTag(TagDto tagDto);
//    void deleteTag(Long id);

}