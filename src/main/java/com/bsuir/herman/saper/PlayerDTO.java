package com.bsuir.herman.saper;

import com.bsuir.herman.saper.entity.Player;

public class PlayerDTO {
    private Long id;
    private String username;
    private int winsScore;
    private int losesScore;

    public PlayerDTO() {
    }

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.losesScore = player.getLosesScore();
        this.username = player.getUsername();
        this.winsScore = player.getWinsScore();
    }

    public PlayerDTO(Long id, String username, int winsScore, int losesScore) {
        this.id = id;
        this.username = username;
        this.winsScore = winsScore;
        this.losesScore = losesScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
