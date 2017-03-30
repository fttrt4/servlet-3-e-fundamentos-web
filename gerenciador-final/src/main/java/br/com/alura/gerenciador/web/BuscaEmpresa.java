package br.com.alura.gerenciador.web;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

public class BuscaEmpresa implements Tarefa {
    public BuscaEmpresa() {
        System.out.println("Construindo uma servlet do tipo BuscaEmpresa " + this.toString());
    }

    // @Override
    // public void destroy() {
    // super.destroy();
    // System.out.println("Destruindo a servlet " + this.toString());
    // }
    //
    // @Override
    // public void init() throws ServletException {
    // super.init();
    // System.out.println("Inicializando a servlet " + this.toString());
    // }

    @Override
    public String executa(final HttpServletRequest request, final HttpServletResponse response) {
        final String filtro = request.getParameter("filtro");
        final Collection<Empresa> empresas = new EmpresaDAO().buscaPorSimilaridade(filtro);
        request.setAttribute("empresas", empresas);
        return "/WEB-INF/paginas/buscaEmpresa.jsp";
    }
}
