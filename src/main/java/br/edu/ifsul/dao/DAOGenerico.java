/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Dariãn
 */
public class DAOGenerico<TIPO> implements Serializable {
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    @PersistenceContext(unitName = "DarianMello_TrabalhoPW_Modelo_Web5PU")
    protected EntityManager em;
    protected Class classesPersistence;

    public DAOGenerico() {
    }
    
    public void persist(TIPO obj) throws Exception {
        em.persist(obj);
    }
    
    public void merge(TIPO obj) throws Exception {
        em.merge(obj);
    }
    
    public TIPO getObjectByID(Object id) throws Exception {
        return (TIPO) em.find(classesPersistence, id);
    }
    
    public void remove (TIPO obj) throws Exception {
        obj = em.merge(obj);
        em.remove(obj);
    }
    
    public List<TIPO> getListaObjetos() {
        String jpql = "from " + classesPersistence.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }

    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<TIPO> getListaTodos() {
        String jpql = "from " + classesPersistence.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }

    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Class getClassesPersistence() {
        return classesPersistence;
    }

    public void setClassesPersistence(Class classesPersistence) {
        this.classesPersistence = classesPersistence;
    }
}
