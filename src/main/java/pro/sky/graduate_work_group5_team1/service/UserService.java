package pro.sky.graduate_work_group5_team1.service;

import pro.sky.graduate_work_group5_team1.exeption.UserNotFoundException;
import pro.sky.graduate_work_group5_team1.model.dto.NewPassword;
import pro.sky.graduate_work_group5_team1.model.dto.ResponseWrapperUser;
import pro.sky.graduate_work_group5_team1.model.dto.UserDto;

import java.util.List;

public interface UserService {

    /**
     * Метод поиска пользователя по Id
     *
     * @param id персональный идентификатор пользователя
     * @return пользователя с данным идентификатором, либо {@link UserNotFoundException}
     */
    UserDto getUser(Integer id);

    /**
     * Метод поиска всех пользователей
     *
     * @return экземпляр {@link ResponseWrapperUser}, внутри которого содержится {@link List<UserDto>}
     */
    ResponseWrapperUser getUsers();

    /**
     * Метод для замены пароля пользователя
     *
     * @param newPassword сущность, содержащая текущий и новый пароль пользователя
     * @return экземпляр {@link NewPassword}
     */
    NewPassword setPassword(NewPassword newPassword);

    /**
     * Метод для изменения данных
     *
     * @param userDto сущность, содержащая изменённые данные пользователя
     * @return экземпляр {@link UserDto}
     */
    UserDto updateUser(UserDto userDto);

}
