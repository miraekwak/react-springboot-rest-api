package com.example.backend.repository;

import com.example.backend.model.Member;

import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Optional<Member> findByUsernameAndPasswd(String username, String passwd);

    Optional<Member> findByUsername(String username);

}
