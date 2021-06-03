package com.bsuir.herman.ws;

import com.bsuir.herman.auth.Debug;
import com.bsuir.herman.auth.model.User;
import com.bsuir.herman.auth.service.UserService;
import com.bsuir.herman.saper.entity.Room;
import com.bsuir.herman.saper.entity.WebPlayer;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebSocketHandler extends AbstractWebSocketHandler {

    private int messageCounter = 0;

    private final String ON_CONNECTED = "ON_CONNECTED";
    private final String JOIN_REQUEST = "JOIN_REQUEST";
    private final String LEFT_ROOM_REQUEST = "LEFT_ROOM_REQUEST";
    private final String SEND_ROOM_MESSAGE = "SEND_ROOM_MESSAGE";
    private final String DISCONNECT_REQUEST = "DISCONNECT_REQUEST";
    private final String START_GAME = "START_GAME";

    //for responses
    private final String JOIN_RESPONSE = "JOIN_RESPONSE";
    private final String MESSAGE_RESPONSE = "MESSAGE_RESPONSE";
    private final String NOTIFY_ROOM_UPDATE = "NOTIFY_ROOM_UPDATE";


    static Map<Long, WebPlayer> playersMap = new HashMap<>();

    UserService userService;
    GameManager gameManager;

    public WebSocketHandler(UserService userService) {
        this.userService = userService;
        this.gameManager = new GameManager();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Debug.printWsInfo("Connection " + session + " was established.");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Long key = findBySession(session);
        closeConnection(key);

        Debug.printWsInfo("Connection " + session + " was closed. Player with id(key) " + key + " was removed.");
    }

    private void closeConnection(Long key) {
        gameManager.removePlayer(playersMap.get(key));
        playersMap.remove(key);
    }

    private Long findBySession(WebSocketSession session) {
//        playersMap.containsValue()
        return playersMap.entrySet()
                .stream()
                .filter(entry -> session.equals(entry.getValue().getSession()))
                .map(Map.Entry::getKey)
                .findFirst().get();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String msg = message.getPayload();
        messageCounter++;
        Debug.printWsRequest(messageCounter, msg);

        String[] list = msg.split(";");

        switch (list[0]) {
            case (ON_CONNECTED): {
                Long playerId = Long.parseLong(list[1]);
                Debug.printWsInfo("Connected new member! Player id:" + playerId + " Session:" + session);

                User user = userService.findById(playerId);
                WebPlayer webPlayer = new WebPlayer(user, session);

                playersMap.put(playerId, webPlayer);
                break;
            }
            case (JOIN_REQUEST): {
                Long playerId = Long.parseLong(list[1]);
                int roomId = Integer.parseInt(list[2]);
                WebPlayer webPlayer = playersMap.get(playerId);

                Debug.printWsInfo("Player #" + playerId + " joining room #" + roomId);
                boolean result = gameManager.joinRoom(roomId, webPlayer);
                Debug.printWsInfo("Result:" + result);

                Room room = gameManager.getRoom(roomId);
                notifyRoomUpdate(room);

                sendWSMessage(JOIN_RESPONSE + ";" + result, session);
                break;
            }
            case (LEFT_ROOM_REQUEST): {
                Long playerId = Long.parseLong(list[1]);
                int roomId = Integer.parseInt(list[2]);
                WebPlayer webPlayer = playersMap.get(playerId);

                Debug.printWsInfo("Player #" + playerId + " left room #" + roomId);
                boolean result = gameManager.leaveRoom(roomId, webPlayer);
                Debug.printWsInfo("Result:" + result);

                if (result) {
                    Room room = gameManager.getRoom(roomId);
                    notifyRoomUpdate(room);
                }
                break;
            }
            case (SEND_ROOM_MESSAGE): {
                int playerId = Integer.parseInt(list[1]);
                int roomId = Integer.parseInt(list[2]);
                String str = "";
                if (list.length == 4) str = list[3];

                Debug.printWsInfo("Player #" + playerId + " on room #" + roomId + " sent msg:" + str);
                boolean result = gameManager.writeToRoom(roomId, playerId, str);
                Debug.printWsInfo("Result:" + result);
                break;
            }
            case (DISCONNECT_REQUEST): {
                Long playerId = Long.parseLong(list[1]);

                Debug.printWsInfo("Player #" + playerId + " close connection");
                playersMap.get(playerId).getSession().close();
                Debug.printWsInfo("Result: ?");
                break;
            }
            default: {
                System.out.println("Default: " + msg);
                break;
            }
        }
    }

    private void notifyRoomUpdate(Room room) {
        String uname1 = room.getPlayer(0).getUser().getUsername();
        String uname2 = room.getPlayer(1).getUser().getUsername();
        WebSocketSession session1 = room.getSession(0);
        if (session1 != null) {
            sendWSMessage(NOTIFY_ROOM_UPDATE + ";" + uname1 + ";" + uname2, session1);
        }
        WebSocketSession session2 = room.getSession(1);
        if (session2 != null) {
            sendWSMessage(NOTIFY_ROOM_UPDATE + ";" + uname1 + ";" + uname2, session2);
        }
    }

    private void sendWSMessage(String str, WebSocketSession session) {
        try {
            Debug.printWsResponse(str);
            session.sendMessage(new TextMessage(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}