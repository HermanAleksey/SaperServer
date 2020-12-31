package com.bsuir.herman.saper.entity;

import javax.persistence.*;

@Entity
@Table
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String login;
    private String password;
    private int winsScore;
    private int losesScore;
}
