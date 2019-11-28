package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Promocao;
import com.example.algamoney.api.repository.promocao.PromocaoRepositoryQuery;

public interface PromocaoRepository extends JpaRepository<Promocao, Long>, PromocaoRepositoryQuery {
}
