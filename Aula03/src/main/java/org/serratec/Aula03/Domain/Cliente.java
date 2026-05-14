package org.serratec.Aula03.Domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.serratec.Aula03.enumerated.StatusCliente;
import org.serratec.Aula03.enumerated.TipoCliente;

@Entity
@Table(name = "cliente")
// Configuração de herança para permitir subclasses de Cliente, como ClientePremium
@Inheritance(strategy = InheritanceType.JOINED)
// Configuração da coluna discriminadora para identificar o tipo de cliente (normal ou premium)
@DiscriminatorColumn(name = "cliente_tipo", discriminatorType = DiscriminatorType.STRING)
public class Cliente extends PessoaBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long id;
    
    @Size(max = 60, message = "O nome deve conter no máximo 60 caracteres")
    @Column(name = "nome", nullable = true, length = 60)
    private String nome;
    
    @Email(message = "O email deve ser válido")
    @Column(length = 50)
    private String email;
    
    @NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "O CPF deve ser válido")
    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;
    

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    
    @Embedded
    @Valid
    private DocumentoCliente documentoCliente;
    

    @Enumerated(EnumType.STRING)
    @Valid
    @Column(name = "tipo_cliente")
    private TipoCliente tipo;
    
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Valid
    private StatusCliente status;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String cpf, LocalDate dataNascimento, TipoCliente tipo, StatusCliente status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.tipo = tipo;
        this.status = status;
    }

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public DocumentoCliente getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(DocumentoCliente documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public StatusCliente getStatus() {
        return status;
    }

    public void setStatus(StatusCliente status) {
        this.status = status;
    }
}
