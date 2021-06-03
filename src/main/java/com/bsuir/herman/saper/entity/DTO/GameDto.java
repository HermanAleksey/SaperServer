package com.bsuir.herman.saper.entity.DTO;

import com.bsuir.herman.saper.entity.Room;

public class GameDto {
    private int width;
    private int height;
    private int minesCount;
    private int minutes;
    private int seconds;
    private boolean sameField;

    public GameDto() {
    }

    public GameDto(int width, int height, int minesCount, int minutes, int seconds, boolean sameField) {
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
        this.minutes = minutes;
        this.seconds = seconds;
        this.sameField = sameField;
    }

    public Room toRoom() {
        return new Room(0, this.width,
                this.height, this.minesCount,
                this.minutes, this.seconds,
                this.sameField);
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

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isSameField() {
        return sameField;
    }

    public void setSameField(boolean sameField) {
        this.sameField = sameField;
    }

    @Override
    public String toString() {
        return "Game{" +
                "width=" + width +
                ", height=" + height +
                ", minesCount=" + minesCount +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                ", sameField=" + sameField +
                '}';
    }
}
