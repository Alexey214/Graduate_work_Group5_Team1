package pro.sky.graduate_work_group5_team1.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.dto.AdsDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = AdsMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AdsListMapper {

    List<Ads> toModelList(List<AdsDto> adsDtoList);

    List<AdsDto> toDtoList(List<Ads> adsList);

}
