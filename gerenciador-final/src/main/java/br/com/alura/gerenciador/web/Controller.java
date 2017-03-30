package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/executa")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 4985869016058892850L;

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final String tarefa = request.getParameter("tarefa");
        if (tarefa == null) {
            throw new IllegalArgumentException("Você esqueceu de passar a tarefa");
        }
        final String nomeDaClasse = "br.com.alura.gerenciador.web." + tarefa;
        Tarefa instancia = null;
        try {
            final Class<?> tipo = Class.forName(nomeDaClasse);
            instancia = (Tarefa) tipo.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new ServletException(e);
        }
        final String pagina = instancia.executa(request, response);
        request.getRequestDispatcher(pagina).forward(request, response);
    }
}
