package pro.sky.graduate_work_group5_team1.mapper;

import org.mapstruct.Mapper;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.model.dto.UserDto;


@Mapper(componentModel = "spring", uses = {AdsListMapper.class, AdsCommentListMapper.class})
public interface UserMapper {

    UserDto toDto(User user);

    User toModel(UserDto userDto);

}
