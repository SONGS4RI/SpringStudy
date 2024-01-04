package SpringStudy.SpringStudy.data3;

import SpringStudy.SpringStudy.data2.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {

}
