/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.produto;

import com.example.algamoney.api.model.Produto;
import com.example.algamoney.api.repository.filter.ProdutoFilter;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author fabio
 */
public interface ProdutoRepositoryQuery {

    public List<Produto> filtrarProdutos();

    public List<Produto> filtrarProdutosByNome(String nome);

    public List<Produto> filtrarProdutosByPaginacao(Long id);

    public List<Produto> filtrarProdutosById(int size, int page);

    public List<Produto> filtrar(ProdutoFilter produtoFilter);

    public List<Produto> filtrarProdutoBySubCategoriaById(Long codigo);

    public List<Produto> filtrarProdutoByPromocaoById(Long codigo);

    public List<Produto> filtrarProdutoByPessoaById(Long codigo);

    public Produto filtrarProdutoByCodBarra(String codigoBarra);

    public Page<Produto> filtrarByPaginacao(String nome, Pageable pageable);

}
