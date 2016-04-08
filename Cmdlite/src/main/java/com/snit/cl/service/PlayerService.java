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
    return DB_HELPER.getAllEntity(Player.class);
  }

  public Player createPlayer(String name) {
    Player player = new Player();
    player.setName(name);
    DB_HELPER.savePlayer(player);
    return player;
  }

}
