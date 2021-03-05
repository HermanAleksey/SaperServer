package com.bsuir.herman.saper;

import lombok.Data;

@Data
public class DTOMessage{
    public int id;
    public String message;

    public DTOMessage() {
    }

    public DTOMessage(int id, String message) {
        this.id = id;
        this.message = message;
    }
}