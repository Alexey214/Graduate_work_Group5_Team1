package pro.sky.graduate_work_group5_team1.service;

import pro.sky.graduate_work_group5_team1.model.dto.RegReq;

public interface AuthService {

    /**
     * Метод для входа в систему
     *
     * @param userName имя пользователя(Email)
     * @param password пароль
     * @return возвращает булево значение
     */
    boolean login(String userName, String password);

    /**
     * Метод для регистрации пользователей
     *
     * @param registerReq запрос авторизации, содержащий имя пользователя, пароль и роль.
     * @param role        роль пользователя
     * @return возвращает булево значение
     */
    boolean register(RegReq registerReq, RegReq.RoleEnum role);
}
