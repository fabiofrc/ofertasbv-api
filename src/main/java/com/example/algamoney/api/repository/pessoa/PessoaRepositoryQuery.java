/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.pessoa;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.model.TipoPessoa;
import java.util.List;

/**
 *
 * @author fabio
 */
public interface PessoaRepositoryQuery {

    public List<Pessoa> filtrarPessoas();

    public List<Pessoa> filtrarPessoasByTipo(TipoPessoa tipoPessoa);

}
