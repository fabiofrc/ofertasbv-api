/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.pessoa;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.model.TipoPessoa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Pessoa> filtrarPessoas() {
        Query query = em.createQuery("SELECT p FROM Pessoa p ORDER BY p.id DESC");
        return query.getResultList();
    }

    @Override
    public List<Pessoa> filtrarPessoasByTipo(TipoPessoa tipoPessoa) {
        Query query = em.createQuery("SELECT p FROM Pessoa p WHERE p.tipoPessoa =:tipoPessoa");
        query.setParameter("tipoPessoa", tipoPessoa);
        return query.getResultList();
    }

}
