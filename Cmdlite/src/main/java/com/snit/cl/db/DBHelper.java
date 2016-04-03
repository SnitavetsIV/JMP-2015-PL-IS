package com.snit.cl.db;

import com.snit.cl.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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
    configuration.configure();

    Properties properties = configuration.getProperties();

    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
  }

  public static DBHelper getInstance() {
    return instance;
  }

  @SuppressWarnings("unchecked")
  public List<Player> getAllPlayers() {
    ArrayList<Player> players = new ArrayList<>();
    try (Session session = sessionFactory.openSession()) {
      players.addAll(session.createCriteria(Player.class).list());
    }
    return players;
  }

}
