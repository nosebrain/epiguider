package de.nosebrain.epiguider.model;

import java.util.SortedSet;
import java.util.TreeSet;

public class Series {

  private String name;
  private SortedSet<Season> seasons;

  /**
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * @param name the name to set
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * @return the seasons
   */
  public SortedSet<Season> getSeasons() {
    return this.seasons;
  }

  public void addSeason(final Season season) {
    if (this.seasons == null) {
      this.seasons = new TreeSet<Season>();
    }
    this.seasons.add(season);
  }
}
