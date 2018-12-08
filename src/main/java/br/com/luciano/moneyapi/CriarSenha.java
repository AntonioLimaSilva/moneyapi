package br.com.luciano.moneyapi;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriarSenha {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin"));
    }
}
