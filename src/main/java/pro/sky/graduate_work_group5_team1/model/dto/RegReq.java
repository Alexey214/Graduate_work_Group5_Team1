package pro.sky.graduate_work_group5_team1.model.dto;

import lombok.Data;

/**
 * RegReq
 */
@Data
public class RegReq {

    private String password;

    public enum RoleEnum {ADMIN, USER}

    private RoleEnum role;
    private String username;

}
