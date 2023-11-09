package SpringStudy.SpringStudy.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepo extends JpaRepository<Parent, Long> {
}
