/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.subcategoria;

import com.example.algamoney.api.model.SubCategoria;
import java.util.List;

/**
 *
 * @author fabio
 */
public interface SubCategoriaRepositoryQuery {

    public List<SubCategoria> filtrarSubcategorias();

}
