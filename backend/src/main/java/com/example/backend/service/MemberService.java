package com.example.backend.service;

import com.example.backend.model.Member;

public interface MemberService {

    Member signup(String name, String username, String passwd);

    Member login(String username, String passwd);
}
