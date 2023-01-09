package pro.sky.graduate_work_group5_team1.model.dto;

import lombok.Data;
import pro.sky.graduate_work_group5_team1.model.Image;

import java.util.List;

/**
 * FullAds
 */
@Data
public class FullAds {

    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private List<Image> image;
    private String phone;
    private Integer pk;
    private Integer price;
    private String title;

}
