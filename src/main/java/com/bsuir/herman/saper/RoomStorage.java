package com.bsuir.herman.saper;

import com.bsuir.herman.saper.entity.Room;

import java.util.ArrayList;

public  class RoomStorage {
     public static ArrayList<Room> roomArrayList = new ArrayList<>(10);

     public RoomStorage() {
          roomArrayList.add(new Room(1));
     }
}
