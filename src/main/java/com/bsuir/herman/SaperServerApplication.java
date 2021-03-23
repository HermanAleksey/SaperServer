package com.bsuir.herman;

import com.bsuir.herman.ws.GameManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SaperServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaperServerApplication.class, args);
        GameManager gameManager = new GameManager();
        gameManager.createRoom();
        gameManager.createRoom();
        gameManager.createRoom();
    }
}
