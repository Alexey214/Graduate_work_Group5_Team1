package pro.sky.graduate_work_group5_team1.model.dto;

import lombok.Data;

import java.util.List;

/**
 * ResponseWrapperUser
 */
@Data
public class ResponseWrapperUser {

    private Integer count;
    private List<UserDto> results;

}
