package pro.sky.graduate_work_group5_team1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Ads {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ads_id_seq")
    @SequenceGenerator(name = "ads_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer pk;
    private String description;

    private String image;
    private Integer price;
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "pk", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<AdsComment> adsComment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return Objects.equals(pk, ads.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }
}
