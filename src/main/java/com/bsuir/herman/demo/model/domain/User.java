package com.bsuir.herman.demo.model.domain;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String login;
    private String password;
    private int winsScore;
    private int losesScore;
}
