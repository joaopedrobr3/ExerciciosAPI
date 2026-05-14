package org.serratec.Aula03.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente_premium")
@DiscriminatorValue("PREMIUM")
@PrimaryKeyJoinColumn(name = "id_cliente")
public class ClientePremium extends Cliente {
    
    @NotBlank(message = "O limite de crédito é obrigatório para clientes premium")
    @Column(name = "limite_credito", nullable = false)
    private Double limiteCredito;
    
    @NotBlank(message = "O nível de fidelidade é obrigatório para clientes premium")
    @Column(name = "nivel_fidelidade", length = 50, nullable = false)
    private String nivelFidelidade;

    public ClientePremium() {
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getNivelFidelidade() {
        return nivelFidelidade;
    }

    public void setNivelFidelidade(String nivelFidelidade) {
        this.nivelFidelidade = nivelFidelidade;
    }
}
