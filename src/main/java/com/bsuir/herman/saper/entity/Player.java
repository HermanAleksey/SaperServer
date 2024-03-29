package com.bsuir.herman.saper.entity;

import javax.persistence.*;

@Entity
@Table
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String login;
    private String password;
    private int winsScore;
    private int losesScore;

    public Player() {
    }

    public Player(Long id, String username, String login, String password, int winsScore, int losesScore) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.password = password;
        this.winsScore = winsScore;
        this.losesScore = losesScore;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWinsScore() {
        return winsScore;
    }

    public void setWinsScore(int winsScore) {
        this.winsScore = winsScore;
    }

    public int getLosesScore() {
        return losesScore;
    }

    public void setLosesScore(int losesScore) {
        this.losesScore = losesScore;
    }
}
