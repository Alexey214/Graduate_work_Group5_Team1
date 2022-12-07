package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RegReq
 */
@Data
@NoArgsConstructor
public class RegReq {

    private String password;

    public enum RoleEnum {ADMIN, USER}

    private RoleEnum role;
    private String username;

}
