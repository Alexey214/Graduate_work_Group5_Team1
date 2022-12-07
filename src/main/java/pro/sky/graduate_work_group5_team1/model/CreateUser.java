package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreateUser
 */
@Data
@NoArgsConstructor
public class CreateUser {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;


}
