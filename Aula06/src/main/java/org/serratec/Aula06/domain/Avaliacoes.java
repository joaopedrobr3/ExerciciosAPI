package org.serratec.Aula06.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Avaliacoes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(max = 500, message = "O comentário da avaliação deve conter no máximo 500 caracteres.")
    @NotBlank(message = "O comentário da avaliação é obrigatório.")
    @Column(name = "comentario", length = 500, nullable = false)
    private String comentario;
    
    
    @Min(value = 1, message = "A nota da avaliação deve ser no mínimo 1.")
    @Max(value = 5, message = "A nota da avaliação deve ser no máximo 5.")
    @Column(name = "nota", nullable = false)
    private Integer nota;
    
    @Column (name = "data_avaliacao", nullable = false)
    private LocalDate dataAvaliacao;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }


}
