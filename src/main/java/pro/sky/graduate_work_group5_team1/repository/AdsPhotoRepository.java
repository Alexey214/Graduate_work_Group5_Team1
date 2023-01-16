package pro.sky.graduate_work_group5_team1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.graduate_work_group5_team1.model.AdsPhoto;

@Repository
public interface AdsPhotoRepository extends JpaRepository<AdsPhoto, Long> {

    AdsPhoto findAdsPhotoByAds_Pk(Integer ads_pk);
}
