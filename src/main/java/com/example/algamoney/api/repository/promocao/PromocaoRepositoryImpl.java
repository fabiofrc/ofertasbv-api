/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.promocao;

import com.example.algamoney.api.model.Promocao;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author fabio
 */
public class PromocaoRepositoryImpl implements PromocaoRepositoryQuery {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Promocao> porDia(LocalDate dia) {
        Query query = em.createQuery("SELECT p FROM Promocao p WHERE p.dataRegistro BETWEEN p.dataInicio AND p.dataFinal");
        query.setParameter("dia", dia);
        return query.getResultList();
    }

    @Override
    public List<Promocao> porSemana(LocalDate semana) {
        Query query = em.createQuery("SELECT p FROM Promocao p WHERE p.dataRegistro BETWEEN p.dataInicio AND p.dataFinal");
        query.setParameter("semana", semana);
        return query.getResultList();
    }

    @Override
    public List<Promocao> porMes(LocalDate mes) {
        Query query = em.createQuery("SELECT p FROM Promocao p WHERE p.dataRegistro BETWEEN p.dataInicio AND p.dataFinal");
        query.setParameter("mes", mes);
        return query.getResultList();
    }

    @Override
    public List<Promocao> filtrarPromocoes() {
        Query query = em.createQuery("SELECT p FROM Promocao p ORDER BY p.id DESC");
        return query.getResultList();
    }

    @Override
    public List<Promocao> filtrarPromocoesByPessoa(Long id) {
        Query query = em.createQuery("SELECT p FROM Promocao p JOIN p.pessoa m WHERE m.id =:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

}
