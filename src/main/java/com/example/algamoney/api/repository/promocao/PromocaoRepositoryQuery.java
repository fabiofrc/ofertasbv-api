/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.algamoney.api.repository.promocao;

import com.example.algamoney.api.model.Promocao;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author fabio
 */
public interface PromocaoRepositoryQuery {

    public List<Promocao> porDia(LocalDate dia);

    public List<Promocao> porSemana(LocalDate semana);

    public List<Promocao> porMes(LocalDate mes);
    
    public List<Promocao> filtrarPromocoes();
    
    public List<Promocao> filtrarPromocoesByPessoa(Long id);

}
