package com.snit.cl.service;

import com.snit.cl.db.DBHelper;
import com.snit.cl.entity.Player;

import java.util.List;

/**
 * @author Ilya Snitavets
 */
public class PlayerService {

  private static final PlayerService INSTANCE = new PlayerService();

  private final DBHelper DB_HELPER;

  private PlayerService() {
    DB_HELPER = DBHelper.getInstance();
  }

  public static PlayerService getInstance() {
    return INSTANCE;
  }

  public List<Player> getAllPlayers() {
    return DB_HELPER.findAllEntity(Player.class);
  }

  Player findPlayer(int id) {
    return findPlayer(id, /*withDeleted*/ false);
  }

  Player findPlayer(int id, boolean withDeleted) {
    if (id < 1) {
      return null;
    }
    return DB_HELPER.findEntity(Player.class, id, withDeleted);
  }

  public Player savePlayer(String[] args) {
    if (args == null || args.length < 1) {
      return null;
    }
    Player player = new Player();
    player.setName(args[0]);
    return DB_HELPER.saveOrUpdateEntity(player) ? player : null;
  }

  public Player updatePlayer(int id, String[] args) {
    if (id < 1 || args == null || args.length < 1) {
      return null;
    }
    Player player = findPlayer(id);
    if (player == null) {
      return null;
    }
    player.setName(args[0]);
    return DB_HELPER.saveOrUpdateEntity(player) ? player : null;
  }

  public boolean deletePlayer(int id) {
    if (id < 1) {
      return false;
    }
    Player player = findPlayer(id);
    if (player == null) {
      return false;
    }
    player.setDeleted(true);
    return DB_HELPER.saveOrUpdateEntity(player);
  }
}
