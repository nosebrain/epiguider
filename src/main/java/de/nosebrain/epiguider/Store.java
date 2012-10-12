package de.nosebrain.epiguider;

import java.util.LinkedList;
import java.util.List;

public class Store {
  private String name;
  private List<ParserInfo> parsers;

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
   * @return the parsers
   */
  public List<ParserInfo> getParsers() {
    if (this.parsers == null) {
      this.parsers = new LinkedList<ParserInfo>();
    }
    return this.parsers;
  }

  /**
   * @param parsers the parsers to set
   */
  public void setParsers(final List<ParserInfo> parsers) {
    this.parsers = parsers;
  }

}
