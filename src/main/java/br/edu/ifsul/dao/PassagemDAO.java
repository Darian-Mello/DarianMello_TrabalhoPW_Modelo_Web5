/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Passagem;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Dariãn
 */
@Stateful
public class PassagemDAO<TIPO> extends DAOGenerico<Passagem> implements Serializable {
    public PassagemDAO() {
        super();
        classesPersistence = Passagem.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        
        ordemAtual = listaOrdem.get(0);
        
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
}