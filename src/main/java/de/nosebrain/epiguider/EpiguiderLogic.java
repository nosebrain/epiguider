package de.nosebrain.epiguider;

import java.util.List;

public interface EpiguiderLogic {
  public List<Store> getSavedSeries();

  public void add(Store store);
  
  public void remove(Store store);
}
