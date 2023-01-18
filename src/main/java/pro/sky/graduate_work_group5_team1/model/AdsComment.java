package pro.sky.graduate_work_group5_team1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AdsComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ads_comment_id_seq")
    @SequenceGenerator(name = "ads_comment_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id", nullable = false)
    private Integer id;
    private LocalDateTime createdAt;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ads_id")
    @ToString.Exclude
    private Ads pk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdsComment that = (AdsComment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
