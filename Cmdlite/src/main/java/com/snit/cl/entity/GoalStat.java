package com.snit.cl.entity;

/**
 * @author Ilya Snitavets
 */
public class GoalStat {

    private int id;
    private Game game;
    private Player user;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getUser() {
        return user;
    }

    public void setUser(Player user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "GoalStat{" +
                "id=" + id +
                ", game=" + game +
                ", user=" + user +
                ", score=" + score +
                '}';
    }
}
