# 💳 Desafio PicPay – Backend Simplificado

Este projeto é uma API REST para transferências entre usuários, desenvolvida como solução para o desafio técnico do PicPay. A implementação segue boas práticas de desenvolvimento orientado a domínio e arquitetura limpa, com foco na clareza, escalabilidade e manutenção do código.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- PostgreSQL
- JPA (Hibernate)
- Flyway (controle de migrations)
- Maven

## 🧱 Arquitetura

- Princípios **SOLID**
- **Arquitetura limpa (Clean Architecture)**
- Estrutura de pacotes organizada por **feature/domain** (ex: `user`, `transaction`, etc)

## 📦 Estrutura de Pacotes (Exemplo)

```
src/main/java/com/picpay
├── user
│   ├── UserModel.java
│   ├── UserController.java
│   ├── UserRepository.java
│   └── UserService.java
├── transaction
│   ├── TransactionModel.java
│   ├── TransactionController.java
│   ├── TransactionRepository.java
│   └── TransactionService.java
```

## 📌 Funcionalidades

- Cadastro de usuários (comum ou lojista)
- Listagem de todos os usuários
- Transferência de saldo entre usuários
- Regras de negócio:
  - Apenas usuários comuns podem enviar dinheiro
  - Validação de saldo disponível antes da transferência
  - Validação do valor da transação
  - Autorização simulada por serviço externo

## 🔧 Configuração e Execução

1. Clone o projeto:

```bash
git clone https://github.com/phlimadev/simplified-picpay.git
cd simplified-picpay
```

2. Configure o banco de dados PostgreSQL no arquivo `application.properties`.

3. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

> As migrations do Flyway serão aplicadas automaticamente ao iniciar.

## 📬 Principais Endpoints

| Método | Rota          | Descrição                  |
| ------ | ------------- | -------------------------- |
| POST   | /users        | Criar um novo usuário      |
| GET    | /users        | Listar todos os usuários   |
| POST   | /transactions | Realizar uma transferência |

## 📄 Migrations

As migrations do banco de dados estão localizadas em:

```
src/main/resources/db/migration
```

Controladas automaticamente pelo Flyway ao subir a aplicação.

---

Desenvolvido por [phlimadev](https://github.com/phlimadev)
