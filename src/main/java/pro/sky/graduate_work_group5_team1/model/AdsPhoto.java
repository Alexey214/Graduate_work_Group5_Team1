package pro.sky.graduate_work_group5_team1.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class AdsPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_id_seq")
    @SequenceGenerator(name = "photo_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdsPhoto adsPhoto = (AdsPhoto) o;
        return Objects.equals(id, adsPhoto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
