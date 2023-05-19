package br.com.undefined.api.entities;


public record Token(
        String token,
        String type,
        String prefix
) {}
