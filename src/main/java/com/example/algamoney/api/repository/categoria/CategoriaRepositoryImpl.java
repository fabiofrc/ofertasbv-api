/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.categoria;

import com.example.algamoney.api.model.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class CategoriaRepositoryImpl implements CategoriaRepositoryQuery {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Categoria> filtrarCategorias() {
        Query query = em.createQuery("SELECT c FROM Categoria c ORDER BY c.id DESC");
        return query.getResultList();
    }

}
