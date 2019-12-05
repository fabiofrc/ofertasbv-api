/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.arquivo;

import com.example.algamoney.api.model.Arquivo;
import java.util.List;

/**
 *
 * @author fabio
 */
public interface ArquivoRepositoryQuery {

    public List<Arquivo> filtrarArquivos();

}
