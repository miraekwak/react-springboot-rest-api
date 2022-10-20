package com.example.backend.controller;

public record CreateLoginRequest(
        String username, String passwd
)
{
}
