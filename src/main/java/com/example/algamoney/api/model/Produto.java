package com.example.algamoney.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "produto", uniqueConstraints = @UniqueConstraint(columnNames = "nome", name = "nome_uk"))
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "foto")
    private String foto;

    @Column(name = "valor_unitario")
    private double valorUnitario;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "isfavorito")
    private boolean isFavorito;

    @Column(name = "data_registro")
    private LocalDate dataRegistro;

    @Column(name = "codigo_barra")
    private String codigoBarra;

    @Column(name = "status")
    private boolean status;

    @Column(name = "unidade")
    private String unidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategoria_id", foreignKey = @ForeignKey(name = "fk_produto_subcategoria"))
    private SubCategoria subCategoria;

//    //@JsonIgnoreProperties("promocaos")
//    @JsonIgnore
//    @ManyToMany(mappedBy = "produtos")
//    private List<Promocao> promocaos;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", columnDefinition = "Integer", foreignKey = @ForeignKey(name = "fk_produto_pessoa"))
    private Pessoa pessoa;

    //@JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Arquivo> arquivos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public boolean getIsFavorito() {
        return isFavorito;
    }

    public void setIsFavorito(boolean isFavorito) {
        this.isFavorito = isFavorito;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public SubCategoria getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }

//    public List<Promocao> getPromocaos() {
//        return promocaos;
//    }
//
//    public void setPromocaos(List<Promocao> promocaos) {
//        this.promocaos = promocaos;
//    }

    public Pessoa getpessoa() {
        return pessoa;
    }

    public void setLoja(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

}
