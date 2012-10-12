package de.nosebrain.epiguider.model;

public class Episode implements Comparable<Episode> {
  private int number;
  private String title;

  /**
   * @return the number
   */
  public int getNumber() {
    return this.number;
  }

  /**
   * @param number
   *          the number to set
   */
  public void setNumber(final int number) {
    this.number = number;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(final String title) {
    this.title = title;
  }

  @Override
  public int compareTo(final Episode o) {
    return this.number - o.number;
  }
}
