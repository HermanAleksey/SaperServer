package com.bsuir.herman.ws;

import com.bsuir.herman.auth.Debug;
import com.bsuir.herman.auth.model.User;
import com.bsuir.herman.auth.service.UserService;
import com.bsuir.herman.saper.entity.WebPlayer;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
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

        gameManager.removePlayer(playersMap.get(key));
        playersMap.remove(key);
//        playersMap.put(key,null);

        Debug.printWsInfo("Connection " + session + " was closed. Player with id(key) "+key+" was removed.");
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

        String[] list = msg.split(":");

        switch (list[0]) {
            case (ON_CONNECTED): {
                Long playerId = Long.parseLong(list[1]);
                Debug.printWsInfo("Connected new member! Player id:" + playerId + " Session:" + session);

                User user = userService.findById(playerId);
                WebPlayer webPlayer = new WebPlayer(user, session);

                playersMap.put(playerId, webPlayer);
                System.out.println("\n\n\n"+playersMap+"\n\n\n");
                break;
            }
            case (JOIN_REQUEST): {
                Long playerId = Long.parseLong(list[1]);
                int roomId = Integer.parseInt(list[2]);

                WebPlayer webPlayer = playersMap.get(playerId);

                Debug.printWsInfo("Player #" + playerId + " joining room #" + roomId);
                boolean result = gameManager.joinRoom(roomId,webPlayer);
                Debug.printWsInfo("Result:"+result);
                break;
            }
            case (LEFT_ROOM_REQUEST): {
                Long playerId = Long.parseLong(list[1]);
                int roomId = Integer.parseInt(list[2]);
                WebPlayer webPlayer = playersMap.get(playerId);

                Debug.printWsInfo("Player #" + playerId + " left room #" + roomId);
                boolean result = gameManager.leaveRoom(roomId,webPlayer);
                Debug.printWsInfo("Result:"+result);
                break;
            }
            case (SEND_ROOM_MESSAGE): {
                int playerId = Integer.parseInt(list[1]);
                int roomId = Integer.parseInt(list[2]);
                String str = "";
                if (list.length == 4) str = list[3];

                Debug.printWsInfo("Player #" + playerId + " on room #" + roomId + " sent msg:" + str);
                boolean result = gameManager.writeToRoom(roomId, playerId, str);
                Debug.printWsInfo("Result:"+result);
                break;
            }
            default: {
                System.out.println("Default:"+msg);
                break;
            }
        }
    }
}