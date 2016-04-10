package com.snit.cl.db;

import com.snit.cl.entity.Game;
import com.snit.cl.entity.Player;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Ilya Snitavets
 */
public class DBHelper {

  private static DBHelper instance = new DBHelper();

  private SessionFactory sessionFactory;

  private DBHelper() {
    Configuration configuration = new Configuration();
    configuration.addAnnotatedClass(Player.class);
    configuration.addAnnotatedClass(Game.class);
    configuration.configure();

    Properties properties = configuration.getProperties();

    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
  }

  public static DBHelper getInstance() {
    return instance;
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> findAllEntity(Class<T> entityClass) {
    ArrayList<T> players = new ArrayList<>();
    try (Session session = sessionFactory.openSession()) {
      Criteria criteria = session.createCriteria(entityClass);
      criteria.add(Restrictions.eq("deleted", false));
      players.addAll(criteria.list());
    } catch (HibernateException e) {
      System.out.println(e);
    }
    return players;
  }

  public boolean saveOrUpdateEntity(Object object) {
    boolean isSuccess;
    if (object == null) {
      return false;
    }
    try (Session session = sessionFactory.openSession()) {
      session.saveOrUpdate(object);
      session.flush();
      isSuccess = true;
    } catch (HibernateException e) {
      isSuccess = false;
      System.out.println(e);
    }
    return isSuccess;

  }

  public <T> T findEntity(Class<T> entityClass, int id) {
    return findEntity(entityClass, id, /*withDeleted*/ false);
  }

  @SuppressWarnings("unchecked")
  public <T> T findEntity(Class<T> entityClass, int id, boolean withDeleted) {
    T entity = null;
    try (Session session = sessionFactory.openSession()) {
      Criteria criteria = session.createCriteria(entityClass);
      if (withDeleted) {
        criteria.add(Restrictions.eq("id", id));
      } else {
        criteria.add(Restrictions.and(Restrictions.eq("deleted", false), Restrictions.eq("id", id)));
      }
      entity = (T) criteria.uniqueResult();
    } catch (HibernateException e) {
      System.out.println(e);
    }
    return entity;
  }

}
