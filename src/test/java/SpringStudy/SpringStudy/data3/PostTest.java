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
//        Post post1 = Post.builder().name("post1").content("post1 content").build();

        Comment comment1 = Comment.builder().content("content 1").build();
        Comment comment2 = Comment.builder().content("content 2").build();
        Comment comment3 = Comment.builder().content("content 3").build();

        commentRepo.save(comment1);
        commentRepo.save(comment2);

        Post post1 = new Post();
        post1.setName("post1");
        post1.setContent("post1. content");
        post1.addComments(comment1);
        post1.addComments(comment2);

        postRepo.save(post1);
        Post changedPost = postRepo.findById(post1.getId()).get();
        changedPost.addComments(comment3);
        postRepo.save(changedPost);
    }
}