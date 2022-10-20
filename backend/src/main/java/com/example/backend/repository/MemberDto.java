package com.example.backend.repository;

import com.example.backend.model.Role;

import java.util.UUID;

public record MemberDto (
        UUID memberId, String name, Role role, String username, String passwd
) {
}
