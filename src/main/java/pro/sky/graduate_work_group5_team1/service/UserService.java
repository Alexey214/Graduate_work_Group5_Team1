package pro.sky.graduate_work_group5_team1.service;

import pro.sky.graduate_work_group5_team1.model.dto.NewPassword;
import pro.sky.graduate_work_group5_team1.model.dto.ResponseWrapperUser;
import pro.sky.graduate_work_group5_team1.model.dto.UserDto;

public interface UserService {

    /**
     * Метод поиска пользователя по Id
     *
     * @param id персональный идентификатор пользователя
     * @return пользователя с данным идентификатором
     */
    UserDto getUser(Integer id);

    ResponseWrapperUser getUsers();

    NewPassword setPassword(NewPassword newPassword);

    UserDto updateUser(UserDto userDto);

}
