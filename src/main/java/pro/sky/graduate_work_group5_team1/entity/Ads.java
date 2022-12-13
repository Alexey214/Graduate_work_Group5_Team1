package pro.sky.graduate_work_group5_team1.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
public class Ads {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private String image;
    private Integer price;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;
    @OneToMany(mappedBy = "ads",fetch = FetchType.LAZY)
    @JoinColumn
    @ToString.Exclude
    private Set<Comment> commentSet;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ads ads = (Ads) o;
        return Objects.equals(id, ads.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
