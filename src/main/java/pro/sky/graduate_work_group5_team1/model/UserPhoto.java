package pro.sky.graduate_work_group5_team1.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class UserPhoto {
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
        UserPhoto userPhoto = (UserPhoto) o;
        return fileSize == userPhoto.fileSize && Objects.equals(id, userPhoto.id) && Objects.equals(mediaType, userPhoto.mediaType) && Arrays.equals(data, userPhoto.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileSize, mediaType);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
