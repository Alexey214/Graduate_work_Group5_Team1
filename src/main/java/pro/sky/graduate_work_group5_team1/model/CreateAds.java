package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CreateAds
 */
@Data
@NoArgsConstructor
public class CreateAds {

    private String description;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;

}
