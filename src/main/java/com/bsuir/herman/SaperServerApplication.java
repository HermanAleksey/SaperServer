package com.bsuir.herman;

import com.bsuir.herman.saper.RoomStorage;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Pattern;

@SpringBootApplication
public class SaperServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaperServerApplication.class, args);
        RoomStorage roomStorage= new RoomStorage();

        /*
        // -----------RegExp for login ------------
        String regexp1 = ".+@.+\\..+";
        String login = "Hahaha@gmail.com";
        System.out.println(login.matches(regexp1));

        // -----------RegExp for password ------------

        Содержит только латинские буквы и цифры
        Должен начинаться с буквы
        Минимум 8 символов

        String regexp2 = "([a-z]+\\w{7,})|([A-Z]+\\w{7,})";
        String password = "Qweqwe";
        System.out.println(password.matches(regexp2));
        // -----------RegExp for nickname ------------
        Содержит только латинские буквы и цифры
        Минимум 4 символа

        String regexp3 = "\\w{4,}";
        String nickname = "Qweqweqe";
        System.out.println(nickname.matches(regexp3));
        */

    }

}
