package com.snit.cl.entity;

/**
 * @author Ilya Snitavets
 */
public class Game {

    private int id = -1;

    private Player blueAttack;
    private Player blueDefence;

    private Player redAttack;
    private Player redDefence;

    private int scoreBlue;
    private int scoreRed;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getBlueAttack() {
        return blueAttack;
    }

    public void setBlueAttack(Player blueAttack) {
        this.blueAttack = blueAttack;
    }

    public Player getBlueDefence() {
        return blueDefence;
    }

    public void setBlueDefence(Player blueDefence) {
        this.blueDefence = blueDefence;
    }

    public Player getRedAttack() {
        return redAttack;
    }

    public void setRedAttack(Player redAttack) {
        this.redAttack = redAttack;
    }

    public Player getRedDefence() {
        return redDefence;
    }

    public void setRedDefence(Player redDefence) {
        this.redDefence = redDefence;
    }

    public int getScoreBlue() {
        return scoreBlue;
    }

    public void setScoreBlue(int scoreBlue) {
        this.scoreBlue = scoreBlue;
    }

    public int getScoreRed() {
        return scoreRed;
    }

    public void setScoreRed(int scoreRed) {
        this.scoreRed = scoreRed;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", blueAttack=" + blueAttack +
                ", blueDefence=" + blueDefence +
                ", redAttack=" + redAttack +
                ", redDefence=" + redDefence +
                ", scoreBlue=" + scoreBlue +
                ", scoreRed=" + scoreRed +
                '}';
    }
}
