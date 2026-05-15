package org.serratec.Aula06.domain;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O título do livro é obrigatório.")
    @Size(max = 100, message = "O título do livro deve conter no máximo 100 caracteres.")
    @Column(length = 100, nullable = false)
    private String titulo;
    
    @Column(name = "isbn", length = 20, unique = true)  
    private String isbn;
    
    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;
    
    @DecimalMin(value = "9.90", message = "O preço do livro deve ser no mínimo R$ 9,90.")
    @Column(name = "preco")
    private BigDecimal preco;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_editora", nullable = false)
    private Editora editora;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "livro")
    private List<Avaliacoes> avaliacoes;

    public Livro() {
    }

    public Livro(String titulo, String isbn, Integer anoPublicacao, BigDecimal preco, Editora editora) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
        this.editora = editora;
    }

    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Avaliacoes> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacoes> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
