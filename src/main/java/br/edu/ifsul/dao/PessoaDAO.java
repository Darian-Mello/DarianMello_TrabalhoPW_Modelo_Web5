/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Pessoa;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Dariãn
 */
@Stateful
public class PessoaDAO<TIPO> extends DAOGenerico<Pessoa> implements Serializable {
    public PessoaDAO() {
        super();
        classesPersistence = Pessoa.class;
        
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("nome", "Nome", "like"));
        
        ordemAtual = listaOrdem.get(1);
        
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);
    }
}
