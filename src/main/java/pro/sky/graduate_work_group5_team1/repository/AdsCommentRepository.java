package pro.sky.graduate_work_group5_team1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.graduate_work_group5_team1.model.AdsComment;

import java.util.Optional;

@Repository
public interface AdsCommentRepository extends JpaRepository<AdsComment, Integer> {

    //    @Query(value = "select ac.* from ads_comment as ac " +
//            "inner join ads on ads.id = 1? " +
//            "inner join users u on u.id = 2?", nativeQuery = true)
    Optional<AdsComment> findAdsCommentByPkAndId(Integer pk, Integer id);

}
