package com.example.algamoney.api.repository;

import com.example.algamoney.api.model.Arquivo;
import com.example.algamoney.api.repository.arquivo.ArquivoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArquivoRepository extends JpaRepository<Arquivo, Long>, ArquivoRepositoryQuery {
}
