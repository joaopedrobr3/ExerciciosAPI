package org.serratec.Aula03.Domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 60, message = "A descrição deve conter no máximo 60 caracteres")
    @Column(name = "descricao", length = 60, nullable = false)
    private String descricao;
    
    @DecimalMin(value = "0.01", message = "O total deve ser maior que zero")
    @NotNull(message = "O total é obrigatório")
    @Column(name = "total", nullable = false)
    private BigDecimal total;
    
    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    public Pedido() {
    }

    public Pedido(Long id, String descricao, BigDecimal total, LocalDate dataPedido) {
        this.id = id;
        this.descricao = descricao;
        this.total = total;
        this.dataPedido = dataPedido;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }




}
