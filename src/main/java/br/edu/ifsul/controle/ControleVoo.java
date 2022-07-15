/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AeroportoDAO;
import br.edu.ifsul.dao.VooDAO;
import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Dariãn
 */
@Named(value = "controleVoo")
@ViewScoped
public class ControleVoo implements Serializable {

    @EJB
    private VooDAO<Voo> dao;
    private Voo objeto;
    
    private VooAgendado vooAgendado;
    private Boolean novoVooAgendado;
    
    private int abaAtiva;
    
    @EJB
    private AeroportoDAO<Aeroporto> daoAeroporto;
    private Aeroporto aeroporto;

    public ControleVoo() {

    }
    
    public void removerAeroporto(Aeroporto obj) {
        objeto.getAeroportos().remove(obj);
        Util.mensagemInformacao("Permissão removido com sucesso!");
    }
    
    public void adicionarAeroporto() {
        if (!objeto.getAeroportos().contains(aeroporto)) {
            objeto.getAeroportos().add(aeroporto);
            Util.mensagemInformacao("Aeroporto adicionado com sucesso!");
        } else {
            Util.mensagemErro("Voo já possui este aeroporto");
        }
    }

    public void novoVooAgendado () {
        vooAgendado = new VooAgendado();
        novoVooAgendado = true;
    }
    
    public void alterarVooAgendado (int index) {
        vooAgendado = objeto.getVooAgendado().get(index);
        novoVooAgendado = false;
    }
    
    public void salvarVooAgendado() {
        if (novoVooAgendado) {
            objeto.adicionarVooAgendado(vooAgendado);
        }
        Util.mensagemInformacao("Voo Agendado adicionado ou alterado com sucesso!");
    }
    
    public void removerVooAgendado(int index) {
        objeto.removerVooAgendado(index);
        Util.mensagemInformacao("Voo Agendado removido com sucesso!");
    }
    
    public String listar() {
        return "/privado/voo/listar?faces-redirect-true";
    }

    public void novo() {
        objeto = new Voo();
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

    public VooDAO<Voo> getDao() {
        return dao;
    }

    public void setDao(VooDAO<Voo> dao) {
        this.dao = dao;
    }

    public Voo getObjeto() {
        return objeto;
    }

    public void setObjeto(Voo objeto) {
        this.objeto = objeto;
    }

    public VooAgendado getVooAgendado() {
        return vooAgendado;
    }

    public void setVooAgendado(VooAgendado vooAgendado) {
        this.vooAgendado = vooAgendado;
    }

    public Boolean getNovoVooAgendado() {
        return novoVooAgendado;
    }

    public void setNovoVooAgendado(Boolean novoVooAgendado) {
        this.novoVooAgendado = novoVooAgendado;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }
    
    public AeroportoDAO<Aeroporto> getDaoAeroporto() {
        return daoAeroporto;
    }
    
    public void setDaoAeroporto(AeroportoDAO<Aeroporto> daoAeroporto) {
        this.daoAeroporto = daoAeroporto;
    }
    
    public Aeroporto getAeroporto() {
        return aeroporto;
    }
    
    public void setAeroporto(Aeroporto aeroporto) {
        this.aeroporto = aeroporto;
    }

}
