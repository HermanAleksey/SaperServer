package com.bsuir.herman.saper;

import com.bsuir.herman.saper.entity.Room;
import com.bsuir.herman.saper.entity.WebPlayer;
import lombok.Synchronized;

import java.util.ArrayList;
import java.util.List;

public class RoomDTO {
    int id;
    PlayerDTO player_1;
    PlayerDTO player_2;

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
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", player_1=" + player_1 +
                ", player_2=" + player_2 +
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
}
