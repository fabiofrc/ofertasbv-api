package com.example.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.algamoney.api.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
