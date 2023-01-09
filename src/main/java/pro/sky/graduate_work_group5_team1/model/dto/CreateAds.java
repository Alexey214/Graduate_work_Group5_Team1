package pro.sky.graduate_work_group5_team1.model.dto;

import lombok.Data;
import pro.sky.graduate_work_group5_team1.model.Image;

import java.util.List;

/**
 * CreateAds
 */
@Data
public class CreateAds {

    private String description;
    private List<Image> image;
    private Integer pk;
    private Integer price;
    private String title;

}
