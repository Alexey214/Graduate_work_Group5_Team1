package pro.sky.graduate_work_group5_team1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.graduate_work_group5_team1.model.Ads;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {

}
