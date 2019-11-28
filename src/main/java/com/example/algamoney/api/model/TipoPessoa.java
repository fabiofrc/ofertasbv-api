/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.model;

/**
 *
 * @author fabio
 */
public enum TipoPessoa {
    PESSOAFISICA("PESSOAFISICA"),
    PESSOAJURIDICA("PESSOAJURIDICA");

    private final String descricao;

    TipoPessoa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
