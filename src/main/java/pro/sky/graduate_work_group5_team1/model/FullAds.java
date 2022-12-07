package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FullAds
 */
@Data
@NoArgsConstructor
public class FullAds {

    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer pk;
    private Integer price;
    private String title;

}
