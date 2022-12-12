package pro.sky.graduate_work_group5_team1.model.dto;

import lombok.Data;

import java.util.List;

/**
 * ResponseWrapperAdsComment
 */
@Data
public class ResponseWrapperAdsComment {

    private Integer count;
    private List<AdsCommentDto> results;

}
