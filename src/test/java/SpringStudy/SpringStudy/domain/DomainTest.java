package SpringStudy.SpringStudy.domain;

import SpringStudy.SpringStudy.data.ChildBothSide;
import SpringStudy.SpringStudy.data.ChildBothSideRepo;
import SpringStudy.SpringStudy.data.ChildOneSide;
import SpringStudy.SpringStudy.data.ChildOneSideRepo;
import SpringStudy.SpringStudy.data.Parent;
import SpringStudy.SpringStudy.data.ParentRepo;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class DomainTest {
    @Autowired ParentRepo parentRepo;
    @Autowired ChildOneSideRepo childOneSideRepo;
    @Autowired ChildBothSideRepo childBothSideRepo;
    @Test
    public void test1() {
        // 영속성 전이 때문에 child만 등록했는데도 db에 저장된다
        Parent parent1 = Parent.builder().name("parent1").build();
        ChildOneSide child1 = ChildOneSide.builder().name("child1").parent(parent1).build();
        childOneSideRepo.save(child1);

    }

}
