package playground.blog.mapper;

import playground.blog.dto.tag.TagDto;
import playground.blog.entity.Tag;

public interface TagMapper {
    TagDto toResponse(Tag tag);
    Tag toEntity(TagDto tagDto);

}
