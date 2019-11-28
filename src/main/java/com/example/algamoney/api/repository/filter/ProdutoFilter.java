/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.filter;

/**
 *
 * @author PMBV-163979
 */
public class ProdutoFilter {

    private String nome;
    private Long prdutoId;
    private Long subCategoriaId;
    private Long promocaoId;
    private Long pessoaId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPrdutoId() {
        return prdutoId;
    }

    public void setPrdutoId(Long prdutoId) {
        this.prdutoId = prdutoId;
    }

    public Long getSubCategoriaId() {
        return subCategoriaId;
    }

    public void setSubCategoriaId(Long subCategoriaId) {
        this.subCategoriaId = subCategoriaId;
    }

    public Long getPromocaoId() {
        return promocaoId;
    }

    public void setPromocaoId(Long promocaoId) {
        this.promocaoId = promocaoId;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

}
