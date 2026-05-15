package org.serratec.Aula06.domain;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Editora {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da editora é obrigatório.")
    @Size(max = 80, message = "O nome da editora deve conter no máximo 80 caracteres.")
    @Column(length = 80, nullable = false)
    private String nome;
    
    @NotBlank(message = "O CNPJ da editora é obrigatório.")
    @Size(max = 18, message = "O CNPJ da editora deve conter no máximo 18 caracteres.")
    @Column(length = 18, nullable = false, unique = true)
    private String cnpj;
    
    @Column(length = 50)
    private String cidade;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "editora")
    private List<Livro> livros; 
    

    public Editora() {
    }


   public Editora(String nome, String cnpj, String cidade) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
