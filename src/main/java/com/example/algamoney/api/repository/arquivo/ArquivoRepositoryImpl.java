/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.arquivo;

import com.example.algamoney.api.model.Arquivo;
import com.example.algamoney.api.model.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class ArquivoRepositoryImpl implements ArquivoRepositoryQuery {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Arquivo> filtrarArquivos() {
        Query query = em.createQuery("SELECT a FROM Arquivo a ORDER BY a.id DESC");
        return query.getResultList();
    }

}
