package de.nosebrain.epiguider.parser;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import de.nosebrain.epiguider.model.Series;

public class FernsehserienParserTest {

  private static final FernsehserienParser PARSER = new FernsehserienParser();
  
  @Test
  public void testParse() throws IOException {
    final Series series = PARSER.parse("navy-cis");
    assertEquals("Navy CIS", series.getName());
    assertEquals(10, series.getSeasons().size());
  }

}
