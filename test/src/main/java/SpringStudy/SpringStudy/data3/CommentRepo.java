package SpringStudy.SpringStudy.data3;

import SpringStudy.SpringStudy.data2.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
