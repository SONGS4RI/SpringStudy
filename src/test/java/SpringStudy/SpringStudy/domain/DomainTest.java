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
        System.out.print("id:" + child1.getId() + "\n");
        childOneSideRepo.save(child1);
        Long id = child1.getId();
        System.out.print("id:" + id + "\n");
    }

    @Test
    public void test2() {
        Parent parent1 = Parent.builder().name("parent1").build();
        ChildBothSide child1 = ChildBothSide.builder().name("child1").parent(parent1).build();
        ChildBothSide child2 = ChildBothSide.builder().name("child2").parent(parent1).build();
        parentRepo.save(parent1);
//        parentRepo.deleteById(parent1.getId());
    }

    @Test
    public void test3() {
        // 다인 쪽에서 지우면 일 인쪽의 데이터에 영향을 끼치지 않고 지운다.
//        childBothSideRepo.deleteById(new Long(10));
        parentRepo.deleteById(new Long(14));
    }

}
