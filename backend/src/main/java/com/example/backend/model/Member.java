package com.example.backend.model;

import java.util.UUID;

public class Member {
    private final UUID memberId;
    private final String name;
    private final Role role;
    private final String username;
    private final String passwd;

    public Member(UUID memberId, String name, Role role, String username, String passwd) {
        this.memberId = memberId;
        this.role = role;
        this.name = name;
        this.username = username;
        this.passwd = passwd;
    }

    public Member(UUID memberId, String name, String username, String passwd) {
        this.memberId = memberId;
        this.role = Role.USER;
        this.name = name;
        this.username = username;
        this.passwd = passwd;
    }

    public UUID getMemberId() {
        return memberId;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }
}
