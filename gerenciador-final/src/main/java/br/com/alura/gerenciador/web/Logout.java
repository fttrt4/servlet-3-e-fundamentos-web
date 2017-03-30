package br.com.alura.gerenciador.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Tarefa {
    // private void doPostComCookies(final HttpServletRequest request, final
    // HttpServletResponse response) throws ServletException,
    // IOException {
    // final Cookie cookie = new
    // Cookies(request.getCookies()).buscaUsuarioLogado();
    // Login.usuariosLogados.get(cookie.getValue());
    //
    // final PrintWriter writer = response.getWriter();
    // if (cookie == null) {
    // writer.println("<html><body>Usuário não estava logado</body></html>");
    // } else {
    // cookie.setMaxAge(0);
    // response.addCookie(cookie);
    // writer.println("<html><body>Deslogado com sucesso</body></html>");
    // }
    // }

    @Override
    public String executa(final HttpServletRequest request, final HttpServletResponse response) {
        request.getSession().removeAttribute("usuarioLogado");
        // request.getSession().invalidate();
        // response.sendRedirect("logout");
        return "/WEB-INF/paginas/logout.html";
    }
}
