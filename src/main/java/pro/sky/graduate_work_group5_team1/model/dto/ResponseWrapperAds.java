package pro.sky.graduate_work_group5_team1.model.dto;

import lombok.Data;

import java.util.List;

/**
 * ResponseWrapperAds
 */
@Data
public class ResponseWrapperAds {

    private Integer count;
    private List<AdsDto> results;

}
