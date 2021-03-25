package com.bsuir.herman.ws;

import com.bsuir.herman.auth.Debug;
import com.bsuir.herman.saper.entity.Room;
import com.bsuir.herman.saper.entity.WebPlayer;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    static int roomCounter = 1;
    static List<Room> rooms = new ArrayList<>();

    public List<Room> getAllRooms() {
        return rooms;
    }

    public void deleteRoom(int id) {
        rooms.removeIf(room -> room.getId() == id);
    }

    public boolean writeToRoom(int roomId, int playerId, String msg) {
        Room room = getRoom(roomId);

        if (room == null) return false;
        return room.writeToChat(playerId, msg);
    }

    public boolean joinRoom(int roomId, WebPlayer player) {
        Room room = getRoom(roomId);

        if (room == null) return false;
        return room.addPlayer(player);
    }

    public boolean leaveRoom(int roomId, WebPlayer player) {
        Room room = getRoom(roomId);

        if (room == null) return false;
        return room.removePlayer(player);
    }

    public Room createRoom() {
        Room room = new Room(roomCounter);
        roomCounter++;
        rooms.add(room);

        Debug.printInfo("Room created. Total room number:" + rooms.size());
        return room;
    }

    private Room getRoom(int roomId) {
        for (Room room : rooms) {
            if (room.getId() == roomId)
                return room;
        }
        return null;
    }

    public void removePlayer(WebPlayer webPlayer) {
        for (Room r :
                rooms) {
            r.removePlayer(webPlayer);
        }
    }
}
