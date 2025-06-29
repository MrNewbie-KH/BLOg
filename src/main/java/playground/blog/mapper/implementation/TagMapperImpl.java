package playground.blog.mapper.implementation;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import playground.blog.dto.tag.TagDto;
import playground.blog.entity.Tag;
import playground.blog.mapper.TagMapper;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor

public class TagMapperImpl implements TagMapper {
   @Override
    public Tag toEntity(TagDto tagDto) {
       return Tag.builder()
               .name(tagDto.getName())
               .createdAt(new Timestamp(System.currentTimeMillis()))
               .updatedAt(new Timestamp(System.currentTimeMillis()))
               .build();
    };
   @Builder
    public TagDto toResponse(Tag tag){
       return TagDto.builder()
               .id(tag.getId())
               .name(tag.getName())
               .build();
   };
}
