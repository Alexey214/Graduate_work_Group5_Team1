package pro.sky.graduate_work_group5_team1.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * AdsComment
 */
@Data
public class AdsCommentDto {

    private Integer author;
    private LocalDateTime createdAt;
    private Integer pk;
    private String text;

}
