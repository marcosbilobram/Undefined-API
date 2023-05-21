package br.com.undefined.api.entities;


public record Credential(String email, String senha) {

    /*public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }*/

}
