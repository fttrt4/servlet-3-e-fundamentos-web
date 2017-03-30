package br.com.alura.gerenciador.web;

import javax.servlet.http.Cookie;

public class Cookies {
    private final Cookie[] cookies;

    public Cookies(final Cookie[] cookies) {
        this.cookies = cookies;
    }

    public Cookie buscaUsuarioLogado() {
        if (this.cookies == null) {
            return null;
        }
        for (final Cookie cookie : this.cookies) {
            if (cookie.getName().equals("usuarioLogado")) {
                return cookie;
            }
        }
        return null;
    }
}
