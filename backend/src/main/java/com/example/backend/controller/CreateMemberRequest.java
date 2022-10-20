package com.example.backend.controller;

public record CreateMemberRequest (
        String name, String username, String passwd
)
{
}
