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
    private int width;
    private int height;
    private int minesCount;
    private int timeMin;
    private int timeSec;
    private boolean sameField;
    private List<String> gameLog;

    public Room(int id) {
        this.id = id;
        playerArray = new WebPlayer[2];
        gameLog = new ArrayList<>();
        width = 8;
        height = 8;
        minesCount = 8;
        timeMin = 5;
        timeSec = 0;
        sameField = false;
    }

    public Room(int id, int width, int height, int minesCount, int timeMin, int timeSec, boolean sameField) {
        this.id = id;
        this.playerArray = new WebPlayer[2];
        this.gameLog = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
        this.timeMin = timeMin;
        this.timeSec = timeSec;
        this.sameField = sameField;
    }

    public WebPlayer getPlayer(int number) {
        if (playerArray[number] != null) return playerArray[number];
        return new WebPlayer();
    }

    public WebSocketSession getSession(int number) {
        if (playerArray[number] != null && playerArray[number].session != null) {
            return playerArray[number].session;
        }
        return null;
    }

    public boolean addPlayer(WebPlayer player) {
        if (playerArray[0] == null) {
            playerArray[0] = player;
            return true;
        }
        if (playerArray[1] == null && playerArray[0] != player) {
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
            TextMessage message = new TextMessage("MESSAGE_RESPONSE;From:" + playerId + ";Message:" + msg);
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMinesCount() {
        return minesCount;
    }

    public void setMinesCount(int minesCount) {
        this.minesCount = minesCount;
    }

    public int getTimeMin() {
        return timeMin;
    }

    public void setTimeMin(int timeMin) {
        this.timeMin = timeMin;
    }

    public int getTimeSec() {
        return timeSec;
    }

    public void setTimeSec(int timeSec) {
        this.timeSec = timeSec;
    }

    public boolean isSameField() {
        return sameField;
    }

    public void setSameField(boolean sameField) {
        this.sameField = sameField;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", playerArray=" + Arrays.toString(playerArray) +
                ", width=" + width +
                ", height=" + height +
                ", minesCount=" + minesCount +
                ", timeMin=" + timeMin +
                ", timeSec=" + timeSec +
                ", sameField=" + sameField +
                ", gameLog=" + gameLog +
                '}';
    }
}