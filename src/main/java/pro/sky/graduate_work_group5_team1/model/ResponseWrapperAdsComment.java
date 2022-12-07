package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ResponseWrapperAdsComment
 */
@Data
@NoArgsConstructor
public class ResponseWrapperAdsComment {

    private Integer count;
    private List<AdsComment> results;

}
