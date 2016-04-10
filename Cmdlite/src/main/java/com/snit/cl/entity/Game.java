package com.snit.cl.entity;

import javax.persistence.*;

/**
 * @author Ilya Snitavets
 */
@Entity
@Table(name = "Game")
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private int id = -1;

  @ManyToOne
  @JoinColumn(name = "BlueAttack")
  private Player blueAttack;

  @ManyToOne
  @JoinColumn(name = "BlueDefence")
  private Player blueDefence;

  @ManyToOne
  @JoinColumn(name = "RedAttack")
  private Player redAttack;

  @ManyToOne
  @JoinColumn(name = "RedDefence")
  private Player redDefence;

  @Column(name = "ScoreBlue")
  private int scoreBlue;

  @Column(name = "ScoreRed")
  private int scoreRed;

  @Column(name = "Deleted")
  private boolean deleted;

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

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
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
        (deleted ? ", deleted" : "") +
        '}';
  }
}
