/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ClasseDAO;
import br.edu.ifsul.dao.VooAgendadoDAO;
import br.edu.ifsul.dao.VooDAO;
import br.edu.ifsul.modelo.Passagem;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Classe;

/**
 *
 * @author Dariãn
 */
@Named(value = "controleVooAgendado")
@ViewScoped
public class ControleVooAgendado implements Serializable {

    @EJB
    private VooAgendadoDAO<Voo> dao;
    private VooAgendado objeto;
    @EJB
    private VooDAO<Voo> daoVoo;
    @EJB
    private PessoaDAO<Pessoa> daoPessoa;
    @EJB
    private ClasseDAO<Classe> daoClasse;
    
    private Passagem passagem;
    private Boolean novaPassagem;
    
    private int abaAtiva;

    public ControleVooAgendado() {

    }

    public void novaPassagem () {
        passagem = new Passagem();
        novaPassagem = true;
    }
    
    public void alterarPassagem (int index) {
        passagem = objeto.getPassagens().get(index);
        novaPassagem = false;
    }
    
    public void salvarPassagem() {
        if (novaPassagem) {
            objeto.adicionarPassagem(passagem);
        }
        Util.mensagemInformacao("Passagem adicionada ou alterada com sucesso!");
    }
    
    public void removerPassagem(int index) {
        objeto.removerPassagem(index);
        Util.mensagemInformacao("Passagem removida com sucesso!");
    }
    
    public String listar() {
        return "/privado/vooAgendado/listar?faces-redirect-true";
    }

    public void novo() {
        objeto = new VooAgendado();
        abaAtiva = 0;
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            abaAtiva = 0;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
       try {
           if (objeto.getId() == null){
               dao.persist(objeto);
           } else {
               dao.merge(objeto);
           }
           Util.mensagemInformacao("Objeto persistido com sucesso!");
       } catch (Exception e){
           Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
       }
    }

    public VooAgendadoDAO<Voo> getDao() {
        return dao;
    }

    public void setDao(VooAgendadoDAO<Voo> dao) {
        this.dao = dao;
    }

    public VooAgendado getObjeto() {
        return objeto;
    }

    public void setObjeto(VooAgendado objeto) {
        this.objeto = objeto;
    }

    public Passagem getPassagem() {
        return passagem;
    }

    public void setPassagem(Passagem passagem) {
        this.passagem = passagem;
    }

    public Boolean getNovdaPassagem() {
        return novaPassagem;
    }

    public void setNovaPassagem(Boolean novaPassagem) {
        this.novaPassagem = novaPassagem;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }
    
    public VooDAO<Voo> getDaoVoo() {
        return daoVoo;
    }

    public void setDaoVoo(VooDAO<Voo> daoVoo) {
        this.daoVoo = daoVoo;
    }
    
    public PessoaDAO<Pessoa> getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO<Pessoa> daoPessoa) {
        this.daoPessoa = daoPessoa;
    }
    
    public ClasseDAO<Classe> getDaoClasse() {
        return daoClasse;
    }

    public void setDaoClasse(ClasseDAO<Classe> daoClasse) {
        this.daoClasse = daoClasse;
    }
    
}
