#  API - Gerenciamento de Raquetes

API REST desenvolvida em **Java + Spring Boot** para gerenciamento de:

- Raquetes  
- Clientes  
- Vendedores  
- Vendas  
- Ranking por período  
- Relatório Excel  

Autenticação via **JWT (Bearer Token)**.

---

## 📌 Regras de Negócio

- A venda registra **data/hora automaticamente**
- Cliente elegível recebe **20% de desconto**
- Uma raquete só pode ser vendida **uma única vez**
- Não é permitido excluir cadastro do vendedor no ranking que já realizou vendas
- CPF de vendedor não pode ser duplicado

---

## 🛠 Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 / PostgreSQL
- Apache POI (Excel)
- OpenAPI 3 (Swagger)
- Hibernate
- PostgreSQL / MySQL
- Flyway
- Docker
- Swagger / OpenAPI
- JUnit / Mockito

---

## 📁 Project Structure


```
├── src/
│   ├── main/
│   │   ├── java/com/example/raquetes/
│   │   │   ├── RaquetesApplication.java
│   │   │
│   │   │   ├── config/
│   │   │   │   ├── OpenApiConfig.java
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── JwtConfig.java
│   │   │
│   │   │   ├── resource/
│   │   │   │   ├── AuthResource.java
│   │   │   │   ├── RaqueteResource.java
│   │   │   │   ├── ClienteResource.java
│   │   │   │   ├── VendedorResource.java
│   │   │   │   ├── VendaResource.java
│   │   │   │   ├── RankingResource.java
│   │   │   │   └── RelatorioResource.java
│   │   │
│   │   │   ├── service/
│   │   │   │   ├── AuthService.java
│   │   │   │   ├── RaqueteService.java
│   │   │   │   ├── ClienteService.java
│   │   │   │   ├── VendedorService.java
│   │   │   │   ├── VendaService.java
│   │   │   │   ├── RankingService.java
│   │   │   │   └── RelatorioService.java
│   │   │
│   │   │   ├── repository/
│   │   │   │   ├── RaqueteRepository.java
│   │   │   │   ├── ClienteRepository.java
│   │   │   │   ├── VendedorRepository.java
│   │   │   │   └── VendaRepository.java
│   │   │
│   │   │   ├── entity/
│   │   │   │   ├── Raquete.java
│   │   │   │   ├── Cliente.java
│   │   │   │   ├── Vendedor.java
│   │   │   │   └── Venda.java
│   │   │
│   │   │   ├── dto/
│   │   │   │   ├── request/
│   │   │   │   │   ├── LoginRequestDTO.java
│   │   │   │   │   ├── RaqueteRequestDTO.java
│   │   │   │   │   ├── ClienteRequestDTO.java
│   │   │   │   │   ├── VendedorRequestDTO.java
│   │   │   │   │   └── VendaRequestDTO.java
│   │   │   │   └── response/
│   │   │   │       ├── LoginResponseDTO.java
│   │   │   │       ├── RaqueteResponseDTO.java
│   │   │   │       ├── ClienteResponseDTO.java
│   │   │   │       ├── VendedorResponseDTO.java
│   │   │   │       ├── VendaResponseDTO.java
│   │   │   │       └── RankingResponseDTO.java
│   │   │
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   ├── BusinessException.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   └── UnauthorizedException.java
│   │   │
│   │   │   ├── security/
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   ├── JwtService.java
│   │   │   │   └── CustomUserDetailsService.java
│   │   │
│   │   │   └── util/
│   │   │       ├── DescontoUtil.java
│   │   │       └── ExcelUtil.java
│   │   │
│   │   └── resources/
│   │       ├── application.yml
│   │       └── db/migration/
│   │           ├── V1__create_tables.sql
│   │           ├── V2__add_indexes.sql
│   │           └── V3__insert_initial_data.sql
│   │
│   └── test/
│       └── java/com/example/raquetes/
│           ├── resource/
│           ├── service/
│           └── repository/
│
├── docker/
│   ├── Dockerfile
│   └── docker-compose.yml
│
├── pom.xml
├── .gitignore
└── README.md

```


## 📸 Screenshots

| Site Swagger .yml        | Code .yml (Código)                  |
|--------------|--------------------------------------|
| [editor.swagger.io ](https://editor.swagger.io/) |[spec swagger](https://github.com/luizacerchiari-cloud-solution-architect/API/spec.yml)        |
|

-----------------------------------------------------------------------

# ▶️ Como Rodar o Projeto

## Pré-requisitos

-   Java 17+
-   Maven 3.8+

## Rodar aplicação

Aplicação sobe em: http://localhost:10000 (numero aleatorio)

Swagger disponível em: http://localhost:10000/swagger-ui/index.html após deploy local

Porta escolhida aleatoriamente
------------------------------------------------------------------------

# 🔐 Autenticação

A API usa JWT Bearer Token.

## Login

POST /api/auth/login


------------------------------------------------------------------------

# 📚 Endpoints

## Raquetes

POST /api/raquetes\
GET /api/raquetes

## Clientes

POST /api/clientes\
GET /api/clientes

## Vendedores

POST /api/vendedores\
GET /api/vendedores\
DELETE /api/vendedores/{id}

## Vendas

POST /api/vendas\
GET /api/vendas

## Ranking

GET
/api/vendas/ranking/vendedores?dataInicio=YYYY-MM-DD&dataFim=YYYY-MM-DD

## Relatório Excel

GET /api/relatorios/vendas.xlsx

------------------------------------------------------------------------

# Links

- **Java 17+**
  - https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
  - https://openjdk.org/projects/jdk/17/

- **Spring Boot**
  - https://spring.io/projects/spring-boot

- **Spring Data JPA**
  - https://spring.io/projects/spring-data-jpa

- **Spring Security**
  - https://spring.io/projects/spring-security

- **Hibernate**
  - https://hibernate.org/

- **H2 Database**
  - https://www.h2database.com/

- **PostgreSQL**
  - https://www.postgresql.org/

- **MySQL**
  - https://www.mysql.com/

- **Apache POI**
  - https://poi.apache.org/

- **OpenAPI 3**
  - https://www.openapis.org/

- **Swagger**
  - https://swagger.io/

- **Flyway**
  - https://flywaydb.org/

- **Docker**
  - https://www.docker.com/

- **JUnit**
  - https://junit.org/

- **Mockito**
  - https://site.mockito.org/

## ⚠️ Project Notice

This project is currently **not finished**.


