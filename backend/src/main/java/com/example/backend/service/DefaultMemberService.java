package com.example.backend.service;

import com.example.backend.model.Member;
import com.example.backend.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultMemberService implements MemberService {

    private final MemberRepository memberRepository;

    public DefaultMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member signup(String name, String username, String passwd) {
        return memberRepository.save(new Member(UUID.randomUUID(), name, username, passwd));
    }

    @Override
    public Member login(String username, String passwd) {
        return memberRepository.findByUsernameAndPasswd(username, passwd)
                .orElseThrow(RuntimeException::new);
    }

}
