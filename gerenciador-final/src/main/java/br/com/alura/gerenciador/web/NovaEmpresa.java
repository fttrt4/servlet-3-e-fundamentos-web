package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

@WebServlet(urlPatterns = "/novaEmpresa")
public class NovaEmpresa extends HttpServlet {
    private static final long serialVersionUID = 8147512659241148541L;

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {
        final String nome = request.getParameter("nome");
        final Empresa empresa = new Empresa(nome);
        new EmpresaDAO().adiciona(empresa);
        request.setAttribute("empresa", empresa);
        request.getRequestDispatcher("/WEB-INF/paginas/novaEmpresa.jsp").forward(request, response);
    }
}
