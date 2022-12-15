package pro.sky.graduate_work_group5_team1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pro.sky.graduate_work_group5_team1.model.Ads;
import pro.sky.graduate_work_group5_team1.model.dto.AdsDto;

@Mapper(componentModel = "spring")
public interface AdsMapper {

    @Mapping(source = "author.id", target = "author")
    AdsDto toDto(Ads ads);

    @Mapping(source = "author", target = "author.id")
    Ads toModel(AdsDto adsDto);

}
