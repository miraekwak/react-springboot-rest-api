package com.example.backend.controller.api;

import com.example.backend.controller.CreateLoginRequest;
import com.example.backend.controller.CreateMemberRequest;
import com.example.backend.model.Member;
import com.example.backend.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MemberRestController {

    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/api/v1/signUp")
    public Member signup(@RequestBody CreateMemberRequest memberRequest){
        return memberService.signup(memberRequest.name(), memberRequest.username(), memberRequest.passwd());
    }

    @PostMapping("/api/v1/login")
    public Member login(@RequestBody CreateLoginRequest loginRequest){
        return memberService.login(loginRequest.username(), loginRequest.passwd());
    }

}
