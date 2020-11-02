package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {
    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void onePlayerCorrect() {
        assertEquals(new Player("Lemieux", "PIT", 45, 54).toString(), stats.team("PIT").get(0).toString());
    }

    @Test
    public void searchFindsPlayer() {
        assertEquals(new Player("Kurri",   "EDM", 37, 53).toString(), stats.search("Kurri").toString());
    }

    @Test
    public void searchNotFoundReturnsNull() {
        assertEquals(null, stats.search("Kaaleppi"));
    }

    @Test
    public void topScorers() {
        List<Player> players = readerStub.getPlayers();
        Collections.sort(players);
        assertEquals(players.get(0).toString(), stats.topScorers(1).get(0).toString());
    }

}