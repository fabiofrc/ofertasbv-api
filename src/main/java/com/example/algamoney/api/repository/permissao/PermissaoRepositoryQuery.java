/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.permissao;

import com.example.algamoney.api.model.Permissao;

/**
 *
 * @author fabio
 */
public interface PermissaoRepositoryQuery {

    public Permissao filtrarPermissaoById(Long id);

}
