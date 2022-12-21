package pro.sky.graduate_work_group5_team1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.graduate_work_group5_team1.model.AdsComment;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<AdsComment, Integer> {

    Optional<AdsComment> findAdsCommentByPkAndId(Integer pk, Integer id);

}
