/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.VooAgendado;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Dariãn
 */
@Stateful
public class VooAgendadoDAO<TIPO> extends DAOGenerico<VooAgendado> implements Serializable {
    public VooAgendadoDAO() {
        super();
        classesPersistence = VooAgendado.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("aeronave", "Aeronave", "like"));
        
        ordemAtual = listaOrdem.get(1);
        
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
    
    public VooAgendado getObjectByID(Object id) throws Exception {
        VooAgendado obj = em.find(VooAgendado.class, id);
        obj.getPassagens().size();
        return obj;
    }
}
