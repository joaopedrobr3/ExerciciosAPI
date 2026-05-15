package org.serratec.aula07.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Aluno {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(max = 80, message = "O nome do aluno deve conter no máximo 80 caracteres.")
    @NotBlank(message = "O nome do aluno é obrigatório.")
    @Column(name = "nome", nullable = false)
    private String nome;
    
    @NotBlank(message = "O email do aluno é obrigatório.")
    @Column(name = "email", unique = true)
    private String email;
    
    @NotBlank(message = "O CPF do aluno é obrigatório.")
    @Column(name = "cpf", unique = true)
    private String cpf;
    
    @JsonIgnore
    @ManyToMany(mappedBy = "alunos")
    private List<Curso> cursos;

    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    

}
