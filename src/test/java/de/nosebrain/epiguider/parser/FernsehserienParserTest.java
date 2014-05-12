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
    assertEquals(12, seasons.size());
    final Iterator<Season> iterator = seasons.iterator();
    iterator.next();
    iterator.next();
    final Season thirdSeason = iterator.next();
    assertEquals(3, thirdSeason.getNumber());
    
    final SortedSet<Episode> episodes = thirdSeason.getEpisodes();
    assertEquals(24, episodes.size());
    
    final Iterator<Episode> epiIterator = episodes.iterator();
    epiIterator.next();
    epiIterator.next();
    epiIterator.next();
    final Episode episode = epiIterator.next();
    assertEquals(4, episode.getNumber());
    assertEquals("Sarg aus Eisen", episode.getTitle());
  }
  
  @Test
  public void testParse2() throws IOException {
    final Series series = PARSER.parse("star-wars-the-clone-wars");
    assertEquals(6, series.getSeasons().size());
  }
  
  @Test
  public void testParse3() throws IOException {
    final Series series = PARSER.parse("grimm");
    assertEquals(3, series.getSeasons().size());
  }

}
