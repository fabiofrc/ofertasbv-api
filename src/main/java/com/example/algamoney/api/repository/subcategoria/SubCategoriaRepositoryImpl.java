/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.subcategoria;

import com.example.algamoney.api.model.SubCategoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class SubCategoriaRepositoryImpl implements SubCategoriaRepositoryQuery {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<SubCategoria> filtrarSubcategorias() {
         Query query = em.createQuery("SELECT s FROM SubCategoria s ORDER BY s.id DESC");
        return query.getResultList();
    }

 
}
