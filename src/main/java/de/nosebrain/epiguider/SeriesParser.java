package de.nosebrain.epiguider;

import java.io.IOException;

import de.nosebrain.epiguider.model.Series;

public interface SeriesParser {
  
  public Series parse(final String id) throws IOException;
}
