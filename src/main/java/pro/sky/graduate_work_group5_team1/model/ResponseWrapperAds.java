package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ResponseWrapperAds
 */
@Data
@NoArgsConstructor
public class ResponseWrapperAds {

    private Integer count;
    private List<Ads> results;

}
