package com.bsuir.herman.saper.entity;
/*
Здравствуйте.
Столкнулся с проблемой при написании сервера.
Для игры необходимо чтобы клиенту приходили оповещения, например, о том что второй игрок уже завершил свою игру.
Таким образом первый игрок отправляет на сервер сообщение об этом, а сервер, в свою очередь, должен уведоимть об этом второго игрока.
Почему-то, когда я планировал, как я буду реализоввывать этот функционал, мне думалось что я смогу реализовать это с помощью триггеров от Spring.
Сейчас я не могу понять как это всё реализовать.
Можете, пожалуйста, подсказать, каким образом я могу реализовать такой функционал?
 */
public class Room {
    int id;
    String log;

    public Room() {
        id = 1;
        log = "";
    }

    public Room(int id) {
        this.id = id;
        log = "";
    }

    public Room(int id, String log) {
        this.id = id;
        this.log = log;
    }

    @Override
    public String toString() {
        return "TestRoom{" +
                "id=" + id +
                ", log='" + log + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}