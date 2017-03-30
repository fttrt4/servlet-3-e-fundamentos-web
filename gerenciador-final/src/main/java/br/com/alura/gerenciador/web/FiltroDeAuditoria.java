package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Usuario;

@WebFilter(urlPatterns = "/*")
public class FiltroDeAuditoria implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final Usuario usuarioLogado = (Usuario) request.getSession().getAttribute("usuarioLogado");
        String nomeUsuario = "<nenhum>";
        if (usuarioLogado != null) {
            nomeUsuario = usuarioLogado.getEmail();
        }
        System.out.println("Usuário " + nomeUsuario + " acessando a URI " + request.getRequestURI());
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(final FilterConfig arg0) throws ServletException {
    }

    private void doFilterComCookies(final ServletRequest servletRequest, final ServletResponse servletResponse,
            final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final Cookie cookie = new Cookies(request.getCookies()).buscaUsuarioLogado();
        String usuario = "<nenhum>";
        if (cookie != null) {
            usuario = cookie.getValue();
            cookie.setMaxAge(10 * 60);
            response.addCookie(cookie);
        }
        final String uri = request.getRequestURI();
        System.out.println("Usuário " + usuario + " acessando a URI " + uri);
        chain.doFilter(servletRequest, servletResponse);
    }
}
