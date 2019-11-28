/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.permissao;

import com.example.algamoney.api.model.Permissao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class PermissaoRepositoryImpl implements PermissaoRepositoryQuery {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Permissao filtrarPermissaoById(Long id) {
        Query query = em.createQuery("SELECT p FROM Permissao p WHERE p.id =:id");
        query.setParameter("id", id);
        return (Permissao) query.getSingleResult();
    }

}
