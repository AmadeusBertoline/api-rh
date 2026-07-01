# API Rh— Sistema de Gerenciamento de Funcionários e suas Avaliações

API REST para gerenciamento de funcionários e suas avaliações, 
desenvolvida com Java 21 e Spring Boot 3.

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA + Hibernate
- MySQL 8
- Maven
- Lombok
- Bean Validation
- Springdoc OpenAPI (Swagger)

## Funcionalidades

- Cadastro e gerenciamento de funcionários
- Ativar e desativar funcionários com PATCH
- Cálculo de média de nota de avaliações de um funcionário
- Filtro de busca de funcionários por departamento
- Validação de dados de entrada
- Tratamento de erros centralizado
- Documentação interativa via Swagger UI

## Arquitetura

O projeto segue arquitetura em camadas com separação clara de responsabilidades:

Controller  →  Service  →  Repository  →  Banco de Dados

- **Controller**: recebe requisições HTTP e delega para o Service
- **Service**: contém regras de negócio e orquestra as operações
- **Repository**: responsável pela persistência de dados
- **DTOs**: objetos de transferência que desacoplam a API da entidade

## Como executar

### Pré-requisitos
- Java 21+
- MySQL 8+
- Maven

### Configuração do banco
```sql
CREATE DATABASE financas_db;
```

### Executando a aplicação
```bash
git clone https://github.com/AmadeusBertoline/api-rh.git
cd api-rh
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`

## Documentação da API

Acesse a documentação interativa (Swagger UI):
http://localhost:8080/swagger-ui/index.html

## Endpoints

### Funcionários
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/funcionarios` | Criar novo funcionário |
| GET | `/funcionarios` | Listar todos os funcionários ou listar por departamento |
| GET | `/funcionarios/{id}` | Buscar funcionário por ID |
| PUT | `/funcionário/{id}` | Atualizar funcionário |
| DELETE | `/funcionário/{id}` | Deletar funcionário |
| PATCH | `/funcionário/{id}/ativar` | Ativar funcionário |
| PATCH | `/funcionário/{id}/desativar` | Desativar funcionário |

### Transações
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/avaliacoes` | Criar nova avaliação |
| GET | `/avaliacoes/funcionario/{id}` | Listar todas as avaliações de um funcionário |
| GET | `/avaliacoes/{id}` | Buscar avaliação por id |

## Regras de Negócio

- Funcionário

    dataAdmissao e ativo = true preenchidos automaticamente na criação
    cpf único no sistema
    salario não pode ser negativo
    Funcionário inativo não pode ser avaliado

- Avaliação

    dataAvaliacao preenchida automaticamente na criação
    nota entre 1 e 10 — validar no Service
    Funcionário inexistente → ResourceNotFoundException
    Funcionário inativo → RegraNegocioException

- Inativação

    PATCH /funcionarios/{id}/inativar apenas muda ativo para false, não deleta o registro