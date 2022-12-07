package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;;

import java.util.List;

/**
 * ResponseWrapperUser
 */
@Data
@NoArgsConstructor
public class ResponseWrapperUser {

    private Integer count;
    private List<User> results;

}
