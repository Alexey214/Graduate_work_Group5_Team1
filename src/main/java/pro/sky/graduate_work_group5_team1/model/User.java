package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 */
@Data
@NoArgsConstructor
public class User {

    private String email;
    private String firstName;
    private Integer id;
    private String lastName;
    private String phone;

}
