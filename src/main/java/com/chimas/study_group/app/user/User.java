package com.chimas.study_group.app.user;

public abstract class User {

    private String nickname;
    private int id;
    private String name;
    private String email;

    public User(String email, int id, String name, String nickname) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
