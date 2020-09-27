package de.nosebrain.epiguider.parser;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;
import java.util.Iterator;
import java.util.SortedSet;

import org.junit.Test;

import de.nosebrain.epiguider.model.Episode;
import de.nosebrain.epiguider.model.Season;
import de.nosebrain.epiguider.model.Series;

public class FernsehserienParserTest {

  private static final FernsehserienParser PARSER = new FernsehserienParser();
  
  @Test
  public void testParse() throws IOException {
    final Series series = PARSER.parse("navy-cis");
    assertEquals("Navy CIS", series.getName());
    final SortedSet<Season> seasons = series.getSeasons();
    assertEquals(17, seasons.size());
    final Iterator<Season> iterator = seasons.iterator();
    iterator.next(); // 1
    iterator.next(); // 2
    final Season thirdSeason = iterator.next(); // 3
    assertEquals(3, thirdSeason.getNumber());
    
    final SortedSet<Episode> episodes = thirdSeason.getEpisodes();
    assertEquals(24, episodes.size());
    
    final Iterator<Episode> epiIterator = episodes.iterator();
    epiIterator.next(); // ep 1
    epiIterator.next(); // ep 2
    epiIterator.next(); // ep 3
    final Episode episode = epiIterator.next(); // ep 4
    assertEquals(4, episode.getNumber());
    assertEquals("Sarg aus Eisen", episode.getTitle());
    
    iterator.next(); // 4
    iterator.next(); // 5
    iterator.next(); // 6
    iterator.next(); // 7
    iterator.next(); // 8
    iterator.next(); // 9
    iterator.next(); // 10
    iterator.next(); // 11
    iterator.next(); // 12
    final Season thirteen = iterator.next(); // 13
    assertEquals(13, thirteen.getNumber());
    
    final Episode episode2 = thirteen.getEpisodes().first();
    assertEquals("Mein Spiel, meine Regeln", episode2.getTitle());
  }
  
  @Test
  public void testParse2() throws IOException {
    final Series series = PARSER.parse("star-wars-the-clone-wars");
    assertEquals(7, series.getSeasons().size());
  }
  
  @Test
  public void testParse3() throws IOException {
    final Series series = PARSER.parse("grimm");
    assertEquals(6, series.getSeasons().size());
  }

  @Test
  public void testParse4() throws IOException {
    final Series simpsons = PARSER.parse("die-simpsons");
    assertEquals(32, simpsons.getSeasons().size());
  }

}
