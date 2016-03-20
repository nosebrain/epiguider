package de.nosebrain.epiguider.database.mysql;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.nosebrain.epiguider.Store;
import de.nosebrain.epiguider.database.mysql.param.EpiguiderParam;
import de.nosebrain.epiguider.service.EpiguiderLogic;

@Component
public class MySQLEpiguiderLogic implements EpiguiderLogic {
  
  @Autowired
  private SqlSessionFactory factory;

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
  public Store getStore(final String seriesId, final String parserId) {
    final SqlSession session = this.factory.openSession();
    try {
      final EpiguiderParam param = new EpiguiderParam();
      param.setSeriesId(seriesId);
      param.setParserId(parserId);
      return session.selectOne("getStoreBySeriesIdAndParser", param);
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
