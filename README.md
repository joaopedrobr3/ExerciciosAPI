# 🐶 API - ONG de Adoção de Animais

Trabalho Prático Individual — Desenvolvimento de API RESTful com Spring Boot + PostgreSQL

👤 **Aluno:** João Pedro Carneiro Motta  
🔗 **Repositório:** [github.com/joaopedrobr3/Trabalho-Individual-API](https://github.com/joaopedrobr3/Trabalho-Individual-API)

---

## 📋 Descrição

API RESTful desenvolvida para gerenciar uma ONG de adoção de animais. O sistema permite o cadastro de pessoas interessadas em adotar, animais disponíveis para adoção, endereços, características dos animais e registros de interesse de adoção.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- Swagger / OpenAPI (springdoc-openapi)
- Bean Validation

---

## 📦 Estrutura de Pacotes

```
src/main/java/org/serratec/Ong/
├── controller/       # Recebe requisições HTTP e delega ao Service
├── service/          # Contém a lógica de negócio
├── repository/       # Comunicação com o banco via JPA
├── domain/           # Entidades JPA (mapeamento das tabelas)
├── dto/              # Objetos de transferência de dados (Request/Response)
├── exception/        # Exceções customizadas e handler global
└── config/           # Configurações gerais (Swagger)
```

---

## 🗂️ Entidades Principais

| Entidade | Descrição |
|---|---|
| `Pessoa` | Pessoas interessadas em adotar animais |
| `Endereco` | Endereço vinculado à pessoa |
| `Animal` | Animais disponíveis para adoção |
| `Caracteristica` | Características de personalidade e saúde dos animais |
| `InteresseAdocao` | Registro de interesse de uma pessoa em adotar um animal |

---

## 🔗 Relacionamentos JPA

| Tipo | Entidades |
|---|---|
| `@OneToOne` | Pessoa ↔ Endereco |
| `@OneToMany / @ManyToOne` | Pessoa → InteresseAdocao / Animal → InteresseAdocao |
| `@ManyToMany` | Animal ↔ Caracteristica |

---

## 🚀 Como Rodar o Projeto

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL instalado e rodando

### Passo a passo

1. Clone o repositório:
```bash
git clone https://github.com/joaopedrobr3/Trabalho-Individual-API.git
cd Trabalho-Individual-API
```

2. Crie o banco de dados no PostgreSQL:
```sql
CREATE DATABASE ong;
```

3. Configure o arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ong
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

4. Execute o projeto:
```bash
mvn spring-boot:run
```

5. Acesse a documentação do Swagger:
```
http://localhost:8080/swagger-ui.html
```

---

## 📖 Endpoints

### 🐶 Animais — `/animais`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/animais` | Lista todos os animais |
| GET | `/animais/{id}` | Busca animal por ID com detalhes |
| POST | `/animais` | Cadastra um novo animal |
| PUT | `/animais/{id}` | Atualiza um animal existente |
| DELETE | `/animais/{id}` | Remove um animal |

Exemplo de body para POST:
```json
{
  "nome": "Rex",
  "especie": "CACHORRO",
  "porte": "GRANDE",
  "caracteristicasIds": [1, 2]
}
```

---

### 👤 Pessoas — `/pessoas`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/pessoas` | Lista todas as pessoas |
| GET | `/pessoas/{id}` | Busca pessoa por ID |
| POST | `/pessoas` | Cadastra uma nova pessoa |
| PUT | `/pessoas/{id}` | Atualiza uma pessoa existente |
| DELETE | `/pessoas/{id}` | Remove uma pessoa |

Exemplo de body para POST:
```json
{
  "nome": "João Silva",
  "cpf": "12345678901",
  "telefone": "2199999999",
  "email": "joao@email.com",
  "enderecoId": 1
}
```

---

### 🏠 Endereços — `/enderecos`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/enderecos` | Lista todos os endereços |
| GET | `/enderecos/{id}` | Busca endereço por ID |
| POST | `/enderecos` | Cadastra um novo endereço |
| PUT | `/enderecos/{id}` | Atualiza um endereço existente |
| DELETE | `/enderecos/{id}` | Remove um endereço |

Exemplo de body para POST:
```json
{
  "cidade": "Rio de Janeiro",
  "rua": "Rua das Flores",
  "bairro": "Centro",
  "numero": 123
}
```

---

### 🏷️ Características — `/caracteristicas`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/caracteristicas` | Lista todas as características |
| GET | `/caracteristicas/{id}` | Busca característica por ID |
| POST | `/caracteristicas` | Cadastra uma nova característica |
| PUT | `/caracteristicas/{id}` | Atualiza uma característica |
| DELETE | `/caracteristicas/{id}` | Remove uma característica |

Exemplo de body para POST:
```json
{
  "personalidade": "DOCIL",
  "saude": "JOVEM_SAUDAVEL"
}
```

---

### 💛 Interesses de Adoção — `/interesses`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/interesses` | Lista todos os interesses |
| GET | `/interesses/{id}` | Busca interesse por ID |
| POST | `/interesses` | Registra um interesse de adoção |
| PUT | `/interesses/{id}` | Atualiza um interesse |
| DELETE | `/interesses/{id}` | Remove um interesse |

Exemplo de body para POST:
```json
{
  "dataSolicitacao": "2025-01-10",
  "motivacao": "Tenho muito espaço em casa e amo animais.",
  "tipoMoradia": "CASA",
  "pessoaId": 1,
  "animalId": 1
}
```

---

## ⚠️ Tratamento de Erros

| Status | Situação |
|---|---|
| 400 | Dados inválidos ou campos obrigatórios não preenchidos |
| 404 | Recurso não encontrado |
| 409 | Conflito — dado duplicado |

---

## 📌 Observações

- Não é necessário autenticação para utilizar a API
- Todos os endpoints estão documentados e testáveis via Swagger em `/swagger-ui.html`
- Os campos de Enum aceitam letras maiúsculas ou minúsculas (ex: `"cachorro"` ou `"CACHORRO"`)
