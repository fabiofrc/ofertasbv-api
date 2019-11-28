package com.example.algamoney.api.service;

import com.example.algamoney.api.model.Permissao;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.EnderecoRepository;
import com.example.algamoney.api.repository.PermissaoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.repository.UsuarioRepository;
import com.example.algamoney.api.security.util.MyPasswordEncoder;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Pessoa salvar(Pessoa pessoa) {

        Permissao id = permissaoRepository.getOne(1L);
        pessoa.getUsuario().getPermissoes().clear();
        pessoa.getUsuario().getPermissoes().add(0, id);
        pessoa.getUsuario().setSenha(MyPasswordEncoder.getPasswordEncoder(pessoa.getUsuario().getSenha()));

        System.out.println("Permissão:" + id);
        System.out.println("Email: " + pessoa.getUsuario().getEmail());
        System.out.println("Senha: " + pessoa.getUsuario().getSenha());

//        pessoa.setUsuario(usuarioRepository.save(pessoa.getUsuario()));
//        pessoa.setEndereco(enderecoRepository.save(pessoa.getEndereco()));
        return pessoaRepository.save(pessoa);
        // return pessoa;

    }

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
        return pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
    }

    public Pessoa buscarPessoaPeloCodigo(Long codigo) {
        Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);
        if (!pessoaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva.get();
    }

}
