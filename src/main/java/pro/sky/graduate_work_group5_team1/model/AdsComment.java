package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * AdsComment
 */
@Data
@NoArgsConstructor
public class AdsComment {

    private Integer author;
    private LocalDateTime createdAt;
    private Integer pk;
    private String text;

}
