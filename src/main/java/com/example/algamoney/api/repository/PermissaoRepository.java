package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Permissao;
import com.example.algamoney.api.repository.permissao.PermissaoRepositoryQuery;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissaoRepository extends JpaRepository<Permissao, Long>, PermissaoRepositoryQuery {
}
