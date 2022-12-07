package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LoginReq
 */
@Data
@NoArgsConstructor
public class LoginReq {

    private String password;
    private String username;

}
