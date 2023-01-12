package pro.sky.graduate_work_group5_team1.model;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class AdsPhoto {

    @Id
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    private byte[] data;
    @OneToOne
    @ToStringExclude
    @MapsId
    private Ads ads;

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
