package playground.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playground.blog.dto.tag.TagDto;
import playground.blog.entity.Tag;
import playground.blog.mapper.TagMapper;
import playground.blog.repository.TagRepository;
import playground.blog.service.TagService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    @Override
    public List<TagDto> getAllTags(){
    List<Tag> tags = tagRepository.findAll();
    List<TagDto> tagDtos = new ArrayList<>();
    for (Tag tag : tags) {
       tagDtos.add(tagMapper.toResponse(tag));
    }
    return tagDtos;
    };
//    TagDto getTagById(Long id);
//    TagDto getTagByName(String name);
//    TagDto createTag(TagDto tagDto);
//    TagDto updateTag(TagDto tagDto);
//    void deleteTag(Long id);


}
