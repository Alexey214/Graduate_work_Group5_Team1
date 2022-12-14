package pro.sky.graduate_work_group5_team1.entity;

import lombok.*;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JoinColumn
    @ToString.Exclude
    private Set<Ads> ads;
    @OneToOne(mappedBy = "user")
    @JoinColumn
    @ToString.Exclude
    private AdsComment adsComment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
