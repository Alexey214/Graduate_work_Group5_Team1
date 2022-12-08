package pro.sky.graduate_work_group5_team1.service;

import pro.sky.graduate_work_group5_team1.model.RegReq;

public interface AuthService {

    boolean login(String userName, String password);

    boolean register(RegReq registerReq, RegReq.RoleEnum role);
}
