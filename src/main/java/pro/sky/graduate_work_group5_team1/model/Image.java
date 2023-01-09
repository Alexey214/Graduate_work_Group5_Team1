package pro.sky.graduate_work_group5_team1.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ads_pictures")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_seq")
    @SequenceGenerator(name = "image_id_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    private String filePath;
    private long fileSize;
    private String mediaType;
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ads_id")
    private Ads ads;

}
