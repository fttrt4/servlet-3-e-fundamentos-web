package br.com.alura.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.Usuario;
import br.com.alura.gerenciador.dao.UsuarioDAO;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = -8638354319336286115L;

    static Map<String, Usuario> usuariosLogados = new HashMap<>();

    private void doPostComCookies(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final String email = request.getParameter("email");
        final String senha = request.getParameter("senha");
        final Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
        final PrintWriter writer = response.getWriter();
        if (usuario == null) {
            writer.println("<html><body>Usuário inválido</body></html>");
        } else {
            final String codigoAleatorio = "" + System.currentTimeMillis() + "/" + Math.random();
            Login.usuariosLogados.put(codigoAleatorio, usuario);

            final Cookie cookie = new Cookie("usuarioLogado", usuario.getEmail());
            cookie.setMaxAge(10 * 60);
            response.addCookie(cookie);
            writer.println("<html><html>Usuário logado: " + usuario.getEmail() + "</body></html>");
        }
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final String email = request.getParameter("email");
        final String senha = request.getParameter("senha");
        final Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
        final PrintWriter writer = response.getWriter();
        if (usuario == null) {
            writer.println("<html><body>Usuário inválido</body></html>");
        } else {
            final HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);
            writer.println("<html><html>Usuário logado: " + usuario.getEmail() + "</body></html>");
        }
    }
}
