package br.com.clockify.clockify_api.model;

public record Token(
    String token, 
    String type,
    String email
) {}
