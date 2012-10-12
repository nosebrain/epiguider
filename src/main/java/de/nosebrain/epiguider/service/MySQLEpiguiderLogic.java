package de.nosebrain.epiguider.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.nosebrain.epiguider.EpiguiderLogic;
import de.nosebrain.epiguider.Store;

@Component
public class MySQLEpiguiderLogic implements EpiguiderLogic {
  
  @Autowired
  private SqlSessionFactory factory;

  @SuppressWarnings("unchecked")
  @Override
  public List<Store> getSavedSeries() {
    final SqlSession session = this.factory.openSession();
    try {
      return session.selectList("getSavedSeries");
    } finally {
      session.close();
    }
  }
  
  @Override
  public void add(final Store store) {
    final SqlSession session = this.factory.openSession();
    try {
      session.insert("addStore", store);
      session.commit();
    } finally {
      session.close();
    }
  }
  
  @Override
  public void remove(final Store store) {
    final SqlSession session = this.factory.openSession();
    try {
      session.insert("removeStore", store);
      session.commit();
    } finally {
      session.close();
    }
  }
}
