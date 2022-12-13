package pro.sky.graduate_work_group5_team1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.graduate_work_group5_team1.entity.Comment;

public interface CommentRepository extends JpaRepository <Comment, Long> {
}
