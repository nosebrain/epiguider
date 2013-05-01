package de.nosebrain.epiguider.service;

import java.util.List;

import de.nosebrain.epiguider.Store;

public interface EpiguiderLogic {
  public List<Store> getSavedSeries();

  public void add(Store store);
  
  public void remove(Store store);
}
