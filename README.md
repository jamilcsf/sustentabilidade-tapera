# Sustentabilidade Tapera

Este projeto foi desenvolvido como parte do Projeto Aplicado do curso de Tecnologia em Análise e Desenvolvimento de Sistemas, com o objetivo de analisar as condições sanitárias do bairro Tapera (Florianópolis) sob a ótica do **Objetivo de Desenvolvimento Sustentável 6 (ODS 6)** da ONU.

## 🚀 Tecnologias Utilizadas

O projeto utiliza uma stack robusta para garantir escalabilidade e segurança:

* **Linguagem:** Java 17+
* **Framework:** Spring Boot
* **Segurança:** Spring Security com autenticação via **JSON Web Token (JWT)**
* **Persistência:** Spring Data JPA
* **Banco de Dados:** PostgreSQL
* **Arquitetura:** Layered Architecture (MVC)

## 📂 Estrutura do Projeto

O código está organizado em camadas para garantir a separação de responsabilidades:

* `config/`: Configurações globais da aplicação.
* `controller/`: Endpoints da API.
* `dto/`: Objetos para transferência de dados, garantindo segurança na exposição da API.
* `exception/`: Tratamento centralizado de erros.
* `model/`: Entidades de banco de dados.
* `repository/`: Acesso e persistência de dados.
* `security/`: Configurações de autenticação (JWT) e proteção de endpoints.
* `service/`: Regras de negócio.

## 📋 Funcionalidades Principais

Conforme o planejamento, o sistema implementa:

* **Gestão de Usuários:** Cadastro e autenticação segura via JWT.


* **Canal de Denúncias:** Permite relatar problemas de saneamento básico.


* **Informação e Conscientização:** Acesso a dados sobre a importância do saneamento.


* **Mapeamento e Interação:** Integração para mapeamento de serviços e fórum para a comunidade.



## ⚙️ Como executar

1. Clone o repositório:
`git clone [https://github.com/jamilcsf/sustentabilidade-tapera](https://github.com/jamilcsf/sustentabilidade-tapera)`
2. Configure o seu banco de dados PostgreSQL no arquivo `application.properties`.
3. Rode a aplicação através da sua IDE preferida ou via terminal:
`./mvnw spring-boot:run`

---

*Desenvolvido por: Arthur Moreira, Douglas do Carmo, Gleyson Ferreira e Jamil Cherem.*

---
