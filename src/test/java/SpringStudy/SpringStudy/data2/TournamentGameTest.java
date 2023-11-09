package SpringStudy.SpringStudy.data2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class TournamentGameTest {
    @Autowired GameRepo gameRepo;
    @Autowired TournamentRepo tournamentRepo;


    @Test
    public void clearAll() {
        gameRepo.deleteAll();
        tournamentRepo.deleteAll();
    }
    @Test
    public void test1() {
        Game game1 = Game.builder().gameName("game 1").build();
        Game game2 = Game.builder().gameName("game 2").build();
        Tournament tournament1 = Tournament.builder().tournamentName("Tournament 1").build();
        List<Game> gameList = new ArrayList<>();
        gameList.add(game1);
        gameList.add(game2);
        tournament1.setGames(gameList);
        tournamentRepo.save(tournament1);
    }

    @Test
    public void test2() {
        tournamentRepo.deleteAll();
    }

}