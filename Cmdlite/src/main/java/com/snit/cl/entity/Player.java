package com.snit.cl.entity;

import javax.persistence.*;

/**
 * @author Ilya Snitavets
 */
@Entity
@Table(name = "User")
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private int id = -1;

  @Column(name = "Name")
  private String name;

  @Column(name = "Deleted")
  private boolean deleted;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  @Override
  public String toString() {
    return "Player{" +
        "id=" + id +
        ", name='" + name + '\'' +
        (deleted ? ", deleted" : "") +
        '}';
  }

}
