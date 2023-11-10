package SpringStudy.SpringStudy.data3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostTest {

    @Autowired CommentRepo commentRepo;
    @Autowired PostRepo postRepo;

    @Test
    public void reset() {
        commentRepo.deleteAll();
        postRepo.deleteAll();
    }

    @Test
    public void test1() {
        commentRepo.deleteAll();
        postRepo.deleteAll();
        Comment comment1 = Comment.builder().content("content 1").build();
        Comment comment2 = Comment.builder().content("content 2").build();

        Post post1 = Post.builder().name("post1").content("post1_content1...").build();

        Post post2 = Post.builder().name("post2").content("post2_content2...").build();
        Comment comment3 = Comment.builder().content("content 3").post(post2).build();
        post1.addComments(comment1);
        post1.addComments(comment2);
        postRepo.save(post1);
        commentRepo.save(comment3);

        commentRepo.deleteById(comment1.getId());
    }
}