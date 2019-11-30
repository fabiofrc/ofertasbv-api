/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.produto;

import com.example.algamoney.api.model.Produto;
import com.example.algamoney.api.repository.filter.ProdutoFilter;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author fabio
 */
public class ProdutoRepositoryImpl implements ProdutoRepositoryQuery {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Produto> filtrar(ProdutoFilter filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> n = query.from(Produto.class);

        Path<String> nomePath = n.<String>get("nome");
        Path<Long> produtoIdPath = n.<Long>get("id");
        Path<Long> subCategoriaIdPath = n.join("subCategoria").<Long>get("id");
        Path<Long> promocaoIdPath = n.join("promocaos").<Long>get("id");
        Path<Long> pessoaIdPath = n.join("pessoa").<Long>get("id");

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getNome() != null) {
            Predicate paramentro = criteriaBuilder.like(criteriaBuilder.lower(nomePath), "%" + filter.getNome().toLowerCase() + "%");
            predicates.add(paramentro);
        }

        if (filter.getPrdutoId() != null) {
            Predicate paramentro = criteriaBuilder.le(produtoIdPath, filter.getPrdutoId());
            predicates.add(paramentro);
        }

        if (filter.getSubCategoriaId() != null) {
            Predicate paramentro = criteriaBuilder.le(subCategoriaIdPath, filter.getSubCategoriaId());
            predicates.add(paramentro);
        }

        if (filter.getPromocaoId() != null) {
            Predicate paramentro = criteriaBuilder.le(promocaoIdPath, filter.getPromocaoId());
            predicates.add(paramentro);
        }

        if (filter.getPessoaId() != null) {
            Predicate paramentro = criteriaBuilder.le(pessoaIdPath, filter.getPessoaId());
            predicates.add(paramentro);
        }

        query.where((Predicate[]) predicates.toArray(new Predicate[0]));
        query.orderBy(criteriaBuilder.desc(n.get("id")));
        TypedQuery<Produto> typedQuery = em.createQuery(query);

        return typedQuery.getResultList();
    }

    @Override
    public List<Produto> filtrarProdutoBySubCategoriaById(Long id) {
        Query query = em.createQuery("SELECT p FROM Produto p JOIN p.subCategoria c WHERE c.id =:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Produto> filtrarProdutoByPromocaoById(Long id) {
        Query query = em.createQuery("SELECT p FROM Produto p JOIN p.promocaos o WHERE o.id =:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Produto> filtrarProdutoByPessoaById(Long codigo) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = criteriaBuilder.createQuery(Produto.class);
        Root<Produto> n = query.from(Produto.class);

        Path<Long> promocaoCodigoPath = n.join("promocaos").<Long>get("id");

        List<Predicate> predicates = new ArrayList<>();

        if (codigo != null) {
            Predicate paramentro = criteriaBuilder.le(promocaoCodigoPath, codigo);
            predicates.add(paramentro);
        }

        query.where((Predicate[]) predicates.toArray(new Predicate[0]));
        query.orderBy(criteriaBuilder.desc(n.get("id")));
        TypedQuery<Produto> typedQuery = em.createQuery(query);

        return typedQuery.getResultList();
    }

    @Override
    public List<Produto> filtrarProdutos() {
        Query query = em.createQuery("SELECT p FROM Produto p ORDER BY p.id DESC");
        return query.getResultList();
    }

    @Override
    public Produto filtrarProdutoByCodBarra(String codigoBarra) {
        Query query = em.createQuery("SELECT p FROM Produto p WHERE p.codigoBarra =:codigoBarra");
        query.setParameter("codigoBarra", codigoBarra);
        return (Produto) query.getSingleResult();
    }

}
