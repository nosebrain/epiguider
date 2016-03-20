package de.nosebrain.epiguider.database.mysql.param;

public class EpiguiderParam {
  private String seriesId;
  private String parserId;
  
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
}
