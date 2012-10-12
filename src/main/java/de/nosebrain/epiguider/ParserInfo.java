package de.nosebrain.epiguider;

public class ParserInfo {
  private String parserId;
  private String seriesId;
  
  /**
   * @return the parserId
   */
  public String getParserId() {
    return this.parserId;
  }
  
  /**
   * @param parserId the parserId to set
   */
  public void setParserId(final String parserId) {
    this.parserId = parserId;
  }
  
  /**
   * @return the seriesId
   */
  public String getSeriesId() {
    return this.seriesId;
  }
  
  /**
   * @param seriesId the seriesId to set
   */
  public void setSeriesId(final String seriesId) {
    this.seriesId = seriesId;
  }
}
