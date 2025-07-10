package playground.blog.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import playground.blog.dto.tag.TagDto;
import playground.blog.entity.Tag;
import playground.blog.mapper.TagMapper;
import playground.blog.repository.TagRepository;
import playground.blog.service.TagService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    }

    @Override
    public TagDto createTag(String name){
        TagDto tagDto = new TagDto();
        tagDto.setName(name);
       Tag savedTag= tagRepository.save(tagMapper.toEntity(tagDto));
        return tagMapper.toResponse(savedTag);
    }
     @Override
    public TagDto getTagById(Long id){
        Optional<Tag> foundTag = tagRepository.findById(id);
        if(foundTag.isPresent()){
            System.out.println("-----------------------------");
            return tagMapper.toResponse(foundTag.get());
        }
        else
            throw new EntityNotFoundException("Tag not found with ID: " + id);
     }
    @Override
    public TagDto getTagByName(String name){
        Optional<Tag> foundTag = tagRepository.findByName(name);
        if(foundTag.isPresent()){
            return tagMapper.toResponse(foundTag.get());
        }
        else
            throw new EntityNotFoundException("Tag not found with ID: " + name);
    }
    @Override
    public void deleteTag(Long id){
        Optional<Tag> foundTag = tagRepository.findById(id);
        if(foundTag.isPresent()){
            tagRepository.deleteById(id);
        }
        else
        throw new EntityNotFoundException("Tag not found with ID: " + id);
    }
    @Override
    public TagDto updateTag(Long id, TagDto tagDto){

//        get the tag
        Optional<Tag> foundTag = tagRepository.findById(id);
//        update it with new provided data
        if(foundTag.isPresent()){
            foundTag.get().setName(tagDto.getName());
            return tagMapper.toResponse(tagRepository.save(foundTag.get()));
        }
        else
            throw new EntityNotFoundException("Tag not found with ID: " + id);    }

}
