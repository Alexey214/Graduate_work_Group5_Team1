package pro.sky.graduate_work_group5_team1.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pro.sky.graduate_work_group5_team1.model.AdsComment;
import pro.sky.graduate_work_group5_team1.model.dto.AdsCommentDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = AdsCommentMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsCommentListMapper {

    List<AdsCommentDto> toDtoList(List<AdsComment> adsCommentList);

    List<AdsComment> toModelList(List<AdsCommentDto> adsCommentDtoList);

}
