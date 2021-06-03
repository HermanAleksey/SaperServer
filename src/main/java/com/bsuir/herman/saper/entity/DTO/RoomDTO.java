package com.bsuir.herman.saper.entity.DTO;

import com.bsuir.herman.saper.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomDTO {
    int id;
    PlayerDTO player_1;
    PlayerDTO player_2;
    private int width;
    private int height;
    private int minesCount;
    private int timeMin;
    private int timeSec;
    private boolean sameField;

    public List<RoomDTO> convertList(List<Room> list) {
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room :
                list) {
            roomDTOList.add(new RoomDTO(room));
        }
        return roomDTOList;
    }

    public RoomDTO() {
    }

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.player_1 = new PlayerDTO(room.getPlayer(0).getUser());
        this.player_2 = new PlayerDTO(room.getPlayer(1).getUser());
        this.width = room.getWidth();
        this.height = room.getHeight();
        this.minesCount = room.getMinesCount();
        this.timeMin = room.getTimeMin();
        this.timeSec = room.getTimeSec();
        this.sameField = room.isSameField();
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", player_1=" + player_1 +
                ", player_2=" + player_2 +
                ", width=" + width +
                ", height=" + height +
                ", minesCount=" + minesCount +
                ", timeMin=" + timeMin +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerDTO getPlayer_1() {
        return player_1;
    }

    public void setPlayer_1(PlayerDTO player_1) {
        this.player_1 = player_1;
    }

    public PlayerDTO getPlayer_2() {
        return player_2;
    }

    public void setPlayer_2(PlayerDTO player_2) {
        this.player_2 = player_2;
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
}
