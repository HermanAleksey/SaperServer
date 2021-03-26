package com.bsuir.herman.saper.entity;

import com.bsuir.herman.auth.Debug;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Room {
    private int id;
    private WebPlayer[] playerArray;
    private List<String> gameLog;

    public Room(int id) {
        this.id = id;
        playerArray = new WebPlayer[2];
        gameLog = new ArrayList<>();
    }

    public WebPlayer getPlayer(int number) {
        if (playerArray[number] != null) return playerArray[number];
        return new WebPlayer();
    }

    public WebSocketSession getSession(int number) {
        if (playerArray[number] != null && playerArray[number].session != null) {return playerArray[number].session;}
        return null;
    }

    public boolean addPlayer(WebPlayer player) {
        if (playerArray[0] == null) {
            playerArray[0] = player;
            return true;
        }
        if (playerArray[1] == null && playerArray[0]!= player) {
            playerArray[1] = player;
            return true;
        }
        return false;
    }

    public boolean removePlayer(WebPlayer webPlayer) {
        if (playerArray[0] != null) {
            if (playerArray[0] == webPlayer) {
                playerArray[0] = null;
                return true;
            }
        }
        if (playerArray[1] != null) {
            if (playerArray[1] == webPlayer) {
                playerArray[1] = null;
                return true;
            }
        }
        return false;
    }

    public void addLog(String str) {
        gameLog.add(str);
    }

    public boolean writeToChat(int playerId, String msg) {
        addLog(msg);

        Debug.printInfo("" + this);

        try {
            TextMessage message = new TextMessage("MESSAGE_RESPONSE;From:" + playerId + "\nMessage:" + msg + "\n");
            if (playerArray[0] != null) playerArray[0].session.sendMessage(message);
            if (playerArray[1] != null) playerArray[1].session.sendMessage(message);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getGameLog() {
        return gameLog;
    }

    public void setGameLog(List<String> gameLog) {
        this.gameLog = gameLog;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", playerArray=" + Arrays.toString(playerArray) +
                '}';
    }
}