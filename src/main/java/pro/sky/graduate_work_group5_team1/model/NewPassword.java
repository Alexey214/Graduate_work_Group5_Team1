package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NewPassword
 */
@Data
@NoArgsConstructor
public class NewPassword {

    private String currentPassword;
    private String newPassword;

}
