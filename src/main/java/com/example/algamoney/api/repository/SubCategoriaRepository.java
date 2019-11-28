package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.SubCategoria;
import com.example.algamoney.api.repository.subcategoria.SubCategoriaRepositoryQuery;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Long>, SubCategoriaRepositoryQuery {
}
