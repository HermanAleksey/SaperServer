package com.bsuir.herman.saper.entity;

import com.bsuir.herman.auth.model.User;
import org.springframework.web.socket.WebSocketSession;

public class WebPlayer {
    WebSocketSession session;
    User user;

    public WebPlayer() {
    }

    public WebPlayer(WebPlayer webPlayer) {
        this(webPlayer.user, webPlayer.session);
    }

    public WebPlayer(User user, WebSocketSession session) {
        this.user = user;
        this.session = session;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "WebPlayer{" +
                "session=" + session +
                ", user=" + user +
                '}';
    }
}
