# 🍽️ Ferry Food - Sistema de Gerenciamento de Restaurantes

Ferry Food é uma aplicação backend robusta desenvolvida em **Java com Spring Boot** que implementa um sistema completo de gerenciamento de restaurantes, incluindo funcionalidades de cadastro de cozinhas, estados, cidades e restaurantes com seus endereços.

## 📋 Tabela de Conteúdos

- [Características](#características)
- [Arquitetura](#arquitetura)
- [Tecnologias](#tecnologias)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [API Endpoints](#api-endpoints)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Executando a Aplicação](#executando-a-aplicação)
- [Testes](#testes)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

---

## ✨ Características

- ✅ **Arquitetura Hexagonal (Ports & Adapters)** - Separação clara entre camadas
- ✅ **CRUD Completo** para Cozinhas, Estados, Cidades e Restaurantes
- ✅ **Validação de Negócio** com Domain Services
- ✅ **Mapeamento de Dados** com MapStruct
- ✅ **Persistência em Banco de Dados** MySQL com JPA/Hibernate
- ✅ **Migrations Automáticas** com Flyway
- ✅ **RESTful API** com Spring Web
- ✅ **Injeção de Dependências** com Spring IoC
- ✅ **Transações Gerenciadas** com Spring TX
- ✅ **Tratamento de Exceções** customizado
- ✅ **Code Generation** com Lombok

---

## 🏗️ Arquitetura

O projeto segue o padrão **Hexagonal Architecture** (Ports & Adapters), garantindo desacoplamento entre as camadas:

```
┌─────────────────────────────────────────────────────────────┐
│                   CAMADA DE ENTRADA (Adapter)                │
│              HTTP Controllers / REST Endpoints               │
└──────────────────────────────┬──────────────────────────────┘
                               │
┌──────────────────────────────▼──────────────────────────────┐
│                      CAMADA DE APLICAÇÃO                      │
│         UseCases, DTOs, Mappers, Business Logic             │
└──────────────────────────────┬──────────────────────────────┘
                               │
┌──────────────────────────────▼──────────────────────────────┐
│                      CAMADA DE DOMÍNIO                        │
│    Entities, ValueObjects, Domain Services, Ports (Interfaces)
└──────────────────────────────┬──────────────────────────────┘
                               │
┌──────────────────────────────▼──────────────────────────────┐
│                   CAMADA DE SAÍDA (Adapter)                  │
│        Persistence Adapters, JPA Repositories               │
└──────────────────────────────┬──────────────────────────────┘
                               │
┌──────────────────────────────▼──────────────────────────────┐
│                    CAMADA DE INFRAESTRUTURA                   │
│              MySQL Database, Flyway Migrations              │
└─────────────────────────────────────────────────────────────┘
```

### Camadas

| Camada | Responsabilidade | Tecnologia |
|--------|------------------|-----------|
| **HTTP Input** | Receber requisições HTTP | Spring Web, REST Controllers |
| **Application** | Orquestrar lógica de negócio | UseCases, DTOs, Mappers |
| **Domain** | Regras de negócio | Entities, ValueObjects, Services |
| **Persistence Output** | Persistir dados | JPA, Hibernate, Repositories |
| **Infrastructure** | Banco de dados | MySQL, Flyway |

---

## 🛠️ Tecnologias

| Categoria | Tecnologia | Versão |
|-----------|-----------|--------|
| **Java** | OpenJDK/Oracle JDK | 8+ |
| **Framework** | Spring Boot | 2.1.7 |
| **Banco de Dados** | MySQL | 5.7+ |
| **ORM** | JPA/Hibernate | 5.3.10 |
| **Mapeamento** | MapStruct | 1.5.5 |
| **Migrations** | Flyway | Latest |
| **Build** | Maven | 3.6+ |
| **Lombok** | Redução de Boilerplate | 1.18+ |

### Dependências Principais

```xml
<!-- Spring Boot Starters -->
spring-boot-starter-web
spring-boot-starter-data-jpa
spring-boot-starter-validation

<!-- ORM & Database -->
mysql-connector-java
flyway-core

<!-- Mapeamento de Dados -->
mapstruct

<!-- Code Generation -->
lombok

<!-- Testes -->
spring-boot-starter-test
junit
```

---

## 📋 Pré-requisitos

### Sistema
- **Java 8+** instalado e configurado no PATH
- **Maven 3.6+** instalado e configurado no PATH
- **MySQL 5.7+** em execução
- **Git** para controle de versão

### Requisitos de Banco de Dados
- Servidor MySQL executando
- Usuário com privilégios para criar banco de dados
- Porta padrão MySQL (3306) acessível ou configurável

### Verificar Instalação
```bash
# Java
java -version

# Maven
mvn -version

# MySQL
mysql --version
```

---

## 📦 Instalação

### 1. Clonar o Repositório

```bash
git clone https://github.com/seu-usuario/ferry-food.git
cd ferry-food
```

### 2. Instalar Dependências

```bash
mvn clean install
```

Este comando:
- Remove compilações anteriores
- Baixa todas as dependências Maven
- Compila o projeto
- Executa os testes

### 3. Compilar Projeto

```bash
mvn clean compile
```

---

## ⚙️ Configuração

### Banco de Dados

Edit `src/main/resources/application.yml`:

```yaml
spring:
  application:
    name: ferry-food
  jpa:
    database: MYSQL
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL57Dialect
  datasource:
    url: jdbc:mysql://localhost:3306/zendb?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    username: root
    password: root
    driver-class-name: com.mysql.jdbc.Driver
```

### Variáveis de Ambiente

Para aplicações em produção, use variáveis de ambiente:

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://seu-host:3306/seu-banco
export SPRING_DATASOURCE_PASSWORD=sua-senha
```

### Migrations (Flyway)

As migrations SQL estão em:
```
src/main/resources/db/migration/
src/main/resources/db/testdata/
```

O Flyway executa automaticamente ao iniciar a aplicação.

---

## 🌐 API Endpoints

### Base URL
```
http://localhost:8080
```

### Cozinhas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/cozinhas` | Listar todas as cozinhas |
| `GET` | `/cozinhas/{id}` | Obter cozinha por ID |
| `POST` | `/cozinhas` | Criar nova cozinha |
| `PUT` | `/cozinhas/{id}` | Atualizar cozinha |
| `DELETE` | `/cozinhas/{id}` | Deletar cozinha |

**Exemplo - Criar Cozinha:**
```bash
curl -X POST http://localhost:8080/cozinhas \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Italiana"
  }'
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Italiana"
}
```

---

### Estados

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/estados` | Listar todos os estados |
| `GET` | `/estados/{id}` | Obter estado por ID |
| `POST` | `/estados` | Criar novo estado |
| `PUT` | `/estados/{id}` | Atualizar estado |
| `DELETE` | `/estados/{id}` | Deletar estado |

**Exemplo - Criar Estado:**
```bash
curl -X POST http://localhost:8080/estados \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "São Paulo"
  }'
```

---

### Cidades

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/cidades` | Listar todas as cidades |
| `GET` | `/cidades/{id}` | Obter cidade por ID |
| `POST` | `/cidades` | Criar nova cidade |
| `PUT` | `/cidades/{id}` | Atualizar cidade |
| `DELETE` | `/cidades/{id}` | Deletar cidade |

**Exemplo - Criar Cidade:**
```bash
curl -X POST http://localhost:8080/cidades \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "São Paulo",
    "estadoId": 1
  }'
```

---

### Restaurantes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/restaurantes` | Listar todos os restaurantes |
| `GET` | `/restaurantes/{id}` | Obter restaurante por ID |
| `POST` | `/restaurantes` | Criar novo restaurante |
| `PUT` | `/restaurantes/{id}` | Atualizar restaurante |
| `DELETE` | `/restaurantes/{id}` | Deletar restaurante |

**Exemplo - Criar Restaurante:**
```bash
curl -X POST http://localhost:8080/restaurantes \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Pizzaria do Giovanni",
    "taxaFrete": 5.50,
    "logradouro": "Rua das Flores",
    "numero": "123",
    "complemento": "Apto 101",
    "bairro": "Centro",
    "cep": "01310-100",
    "cidadeId": 1,
    "cozinhaId": 1
  }'
```

**Resposta:**
```json
{
  "id": 1,
  "nome": "Pizzaria do Giovanni",
  "taxaFrete": 5.50,
  "logradouro": "Rua das Flores",
  "numero": "123",
  "complemento": "Apto 101",
  "bairro": "Centro",
  "cep": "01310-100"
}
```

---

## 📁 Estrutura do Projeto

```
ferry-food/
├── src/
│   ├── main/
│   │   ├── java/com/ferry/food/
│   │   │   ├── adapter/
│   │   │   │   ├── config/              # Configurações Spring
│   │   │   │   ├── input/http/
│   │   │   │   │   ├── controller/      # REST Controllers
│   │   │   │   │   ├── advice/          # Exception Handlers
│   │   │   │   │   └── config/          # Config HTTP
│   │   │   │   └── output/persistence/
│   │   │   │       ├── adapter/         # Repository Adapters
│   │   │   │       ├── entity/          # JPA Entities
│   │   │   │       ├── mapper/          # JPA Mappers
│   │   │   │       ├── repository/      # JPA Repositories
│   │   │   │       └── config/          # Config Persistence
│   │   │   ├── application/
│   │   │   │   ├── usecases/           # UseCase Implementations
│   │   │   │   ├── dtos/                # Data Transfer Objects
│   │   │   │   │   ├── input/           # DTOs de Entrada
│   │   │   │   │   └── output/          # DTOs de Saída
│   │   │   │   └── mappers/             # Application Mappers
│   │   │   ├── domain/
│   │   │   │   ├── entities/            # Domain Entities
│   │   │   │   ├── valueobjects/        # Value Objects
│   │   │   │   ├── ports/               # Interfaces (Contracts)
│   │   │   │   │   ├── input/           # Input Ports (UseCases)
│   │   │   │   │   └── output/          # Output Ports (Repositories)
│   │   │   │   ├── domainservices/      # Business Logic Services
│   │   │   │   └── exceptions/          # Custom Exceptions
│   │   │   └── Application.java         # Spring Boot Main Class
│   │   └── resources/
│   │       ├── application.yml           # Configurações
│   │       └── db/
│   │           ├── migration/            # Flyway Migrations
│   │           └── testdata/             # Test Data Scripts
│   └── test/                             # Testes Unitários
├── pom.xml                               # Maven POM
└── README.md                             # Este arquivo
```

### Descrição das Camadas

#### `adapter/input/http/controller/`
Controladores REST que recebem requisições HTTP e as delegam aos UseCases da aplicação.

**Exemplo:**
- `EstadoController.java` - CRUD para Estados
- `CidadeController.java` - CRUD para Cidades
- `CozinhaController.java` - CRUD para Cozinhas
- `RestauranteController.java` - CRUD para Restaurantes

#### `application/usecases/`
Implementações dos casos de uso que orquestram a lógica de negócio.

**Exemplo:**
- `CriarEstadoImpl.java` - Lógica de criação de Estado
- `ListarEstadosImpl.java` - Lógica de listagem de Estados

#### `domain/entities/`
Entidades de domínio que representam os conceitos principais do negócio.

**Exemplo:**
- `Estado.java` - Entidade de Estado
- `Cidade.java` - Entidade de Cidade
- `Restaurante.java` - Entidade de Restaurante

#### `adapter/output/persistence/`
Adapters que implementam os ports definidos no domínio, fornecendo acesso aos dados.

---

## ▶️ Executando a Aplicação

### Opção 1: Maven

```bash
mvn spring-boot:run
```

### Opção 2: Jar

```bash
mvn clean package
java -jar target/ferry-food-0.0.1-SNAPSHOT.jar
```

### Opção 3: IDE (IntelliJ IDEA)

1. Abra o projeto em IntelliJ
2. Navegue até `Application.java`
3. Clique em "Run" (Shift + F10)

### Verificar se está Rodando

```bash
# Verificar se o servidor está respondendo
curl http://localhost:8080/cozinhas

# Você deve receber um JSON vazio ou com dados
[]
```

### Logs

A aplicação exibe logs no console:
```
2026-03-31 12:00:00.000  INFO 12345 --- [           main] c.f.f.Application : Starting Application
2026-03-31 12:00:05.000  INFO 12345 --- [           main] c.f.f.Application : Started Application in 5.123 seconds
```

---

## 🧪 Testes

### Executar Todos os Testes

```bash
mvn test
```

### Executar Testes de uma Classe Específica

```bash
mvn test -Dtest=EstadoControllerTest
```

### Executar com Cobertura

```bash
mvn test jacoco:report
# Relatório em: target/site/jacoco/index.html
```

### Ferramentas Recomendadas para Testes

- **Postman** - Teste HTTP com interface gráfica
- **Curl** - Teste HTTP via linha de comando
- **JUnit 4** - Framework de testes unitários
- **Mockito** - Mocking de dependências

---

## 📚 Conceitos Principais

### Domain-Driven Design (DDD)

O projeto segue princípios de DDD:
- **Entities**: Objetos com identidade única
- **Value Objects**: Objetos sem identidade, imutáveis
- **Domain Services**: Lógica que não pertence a uma entity
- **Aggregates**: Grupos de entities relacionadas
- **Repositories**: Abstração para persistência

### Hexagonal Architecture

Separa a aplicação em 3 áreas:
1. **Driving Side** - Como o mundo exterior se comunica (Controllers)
2. **Core** - Lógica de negócio (UseCases, Entities)
3. **Driven Side** - Como a aplicação se comunica com o exterior (Repositories)

### MapStruct

Framework que mapeia DTOs para Entities automaticamente em tempo de compilação.

```java
@Mapper(componentModel = "spring")
public interface EstadoOutputMapper {
    EstadoDTO toDTO(EstadoJpaEntity entity);
    List<EstadoDTO> toDTOList(List<EstadoJpaEntity> entities);
}
```

---

## 🔍 Troubleshooting

### Erro: "Connection refused" no MySQL

**Solução:**
```bash
# Verificar se MySQL está rodando
mysql -u root -p

# Ou iniciar o serviço
sudo systemctl start mysql  # Linux
brew services start mysql   # macOS
```

### Erro: "Banco de dados não existe"

**Solução:** O Flyway cria automaticamente. Verifique `application.yml`:
```yaml
datasource:
  url: jdbc:mysql://localhost:3306/zendb?createDatabaseIfNotExist=true
```

### Erro: Porta 8080 já está em uso

**Solução:**
```bash
# Usar porta diferente
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### Erro: "Could not find artifact"

**Solução:**
```bash
# Limpar cache Maven
rm -rf ~/.m2/repository
mvn clean install
```

---

## 📊 Métricas do Projeto

| Métrica | Valor |
|---------|-------|
| Total de Linhas de Código | ~4,000 |
| Arquivos Java | 114 |
| Controllers | 4 |
| UseCases | 20 |
| Entities | 6 |
| DTOs | 8 |
| Repositories | 6 |

---

## 🎯 Funcionalidades Futuras

- [ ] Autenticação e Autorização (JWT)
- [ ] Gerenciamento de Produtos/Menu
- [ ] Pedidos e Entregas
- [ ] Avaliações e Comentários
- [ ] Integração com Pagamentos
- [ ] Sistema de Notificações
- [ ] Dashboard Administrativo
- [ ] API GraphQL

---

## 🤝 Contribuindo

### Passos para Contribuir

1. **Fork** o projeto
2. Crie uma **branch** para sua feature (`git checkout -b feature/AmazingFeature`)
3. **Commit** suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. **Push** para a branch (`git push origin feature/AmazingFeature`)
5. Abra um **Pull Request**

### Padrões de Código

- Use **Java 8+**
- Siga **Google Java Style Guide**
- Use **Lombok** para reduzir boilerplate
- Escreva testes para novas funcionalidades
- Mantenha a **Arquitetura Hexagonal**

---

## 📝 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

## 👨‍💻 Autor

**Seu Nome**
- GitHub: [@seu-usuario](https://github.com/seu-usuario)
- Email: seu.email@example.com

---

## 📞 Suporte

Para suporte, abra uma issue no GitHub ou entre em contato através do email.

---

## 🙏 Agradecimentos

- Spring Boot Framework
- MapStruct
- Flyway
- MySQL Community
- Lombok

---

**Última atualização:** 31 de Março de 2026

**Versão:** 1.0.0
