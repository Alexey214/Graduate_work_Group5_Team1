package pro.sky.graduate_work_group5_team1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pro.sky.graduate_work_group5_team1.model.AdsComment;
import pro.sky.graduate_work_group5_team1.model.dto.AdsCommentDto;

@Mapper(componentModel = "spring")
public interface AdsCommentMapper {

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "pk.pk", target = "pk")
    AdsCommentDto toDto(AdsComment adsComment);

    @Mapping(source = "author", target = "author.id")
    @Mapping(source = "pk", target = "pk.pk")
    AdsComment toModel(AdsCommentDto adsCommentDto);

}
