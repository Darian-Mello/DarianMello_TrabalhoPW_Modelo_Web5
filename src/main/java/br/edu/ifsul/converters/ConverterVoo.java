/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.converters;

import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Voo;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dariãn
 */
@Named(value = "converterVoo")
@RequestScoped
public class ConverterVoo implements Serializable, Converter {
    
    @PersistenceContext(unitName = "DarianMello_TrabalhoPW_Modelo_Web5PU")
    private EntityManager em;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Voo.class, Integer.parseInt(string));
    }
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if (t == null){
            return null;
        }
        Voo obj = (Voo) t;
        return obj.getId().toString();
    }
}