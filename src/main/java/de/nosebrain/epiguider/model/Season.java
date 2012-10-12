package de.nosebrain.epiguider.model;

import java.util.SortedSet;
import java.util.TreeSet;

public class Season implements Comparable<Season> {
  private int number;
  private SortedSet<Episode> episodes;

  /**
   * @return the number
   */
  public int getNumber() {
    return this.number;
  }

  /**
   * @param number the number to set
   */
  public void setNumber(final int number) {
    this.number = number;
  }
  
  /**
   * @return the episodes
   */
  public SortedSet<Episode> getEpisodes() {
    return this.episodes;
  }
  
  public void addEpisode(final Episode episode) {
    if (this.episodes == null) {
      this.episodes = new TreeSet<Episode>();
    }
    
    this.episodes.add(episode);
  }

  @Override
  public int compareTo(final Season o) {
    return this.number - o.number;
  }
}
