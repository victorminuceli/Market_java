# Market — Backend

Backend da aplicação **Market**, desenvolvido em Java com Spring Boot.

O sistema disponibiliza a API responsável pelo cadastro e login de usuários, catálogo de produtos, carrinho e pedidos, utilizando MySQL para persistência dos dados.

## Frontend

O frontend da aplicação está em um repositório separado:

[Market — Frontend](https://github.com/victorminuceli/market-frontend)

Para o **guia completo de instalação, configuração e execução da aplicação**, consulte o README do frontend.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Hibernate
- MySQL 8
- Jakarta Validation
- Lombok
- Gradle

## Arquitetura

O backend utiliza uma arquitetura em três camadas:

```text
Controller → Service → Repository → Entity → MySQL
```

- **Controller:** recebe as requisições HTTP e disponibiliza os endpoints.
- **Service:** concentra as regras de negócio.
- **Repository:** realiza o acesso aos dados.
- **Entity:** representa as tabelas persistidas.

## Funcionalidades da API

### Usuários

- Cadastro
- Login
- Listagem
- Consulta por ID
- Atualização
- Exclusão

### Produtos

- Cadastro
- Listagem
- Consulta por ID
- Atualização
- Exclusão

### Carrinho

- Consulta do carrinho
- Adição de produtos
- Alteração de quantidade
- Remoção de produtos
- Cálculo do total

### Pedidos

- Finalização da compra
- Registro dos produtos e preços da compra
- Consulta dos pedidos do usuário
- Consulta dos itens de cada pedido

## Banco de dados

O projeto utiliza **MySQL**.

Banco utilizado:

```text
market
```

As tabelas são criadas ou atualizadas automaticamente pelo Hibernate.

Os produtos iniciais são carregados automaticamente pelo arquivo:

```text
src/main/resources/data.sql
```

O banco deve estar disponível em:

```text
localhost:3306
```

O usuário e a senha são configurados no arquivo:

```text
src/main/resources/application.properties
```

A senha do MySQL deve ser informada através da variável de ambiente:

```text
DB_PASSWORD
```

## Imagens dos produtos

As imagens utilizadas pelo catálogo ficam em:

```text
uploads/produtos/
```

O backend disponibiliza essas imagens através do endereço:

```text
/imagens/produtos/
```

Exemplo:

```text
http://localhost:8080/imagens/produtos/cafe.jpg
```

Uma imagem de exemplo da configuração do IntelliJ está disponível em:

[docs/intellij-config.png](https://github.com/victorminuceli/Market_java/blob/main/market/docs/intellij-config.png)

## Executar o backend

Abra o projeto:

```text
Market_java/market
```

No IntelliJ IDEA, execute:

```text
MarketApplication
```

utilizando **Java 17**.

Também é possível executar pelo PowerShell:

```powershell
$env:DB_PASSWORD = 'SUA_SENHA_DO_MYSQL'
.\gradlew.bat bootRun
```

O backend será iniciado em:

```text
http://localhost:8080
```

Exemplo de endpoint:

```text
http://localhost:8080/produtos
```

## Estrutura do projeto

```text
market/
├── banco/
│   └── produtos.sql          # Script manual/backup do catálogo
├── docs/
│   └── intellij-config.png   # Exemplo de configuração do IntelliJ
├── uploads/
│   └── produtos/             # Imagens dos produtos
├── src/main/
│   ├── java/com/example/market/
│   │   ├── config/           # Configuração do acesso às imagens
│   │   ├── controller/       # Rotas HTTP
│   │   ├── dto/              # Dados recebidos em operações específicas
│   │   ├── model/entity/     # Entidades persistidas
│   │   ├── repository/       # Acesso ao banco
│   │   ├── service/          # Regras de negócio
│   │   └── MarketApplication.java
│   └── resources/
│       ├── application.properties
│       └── data.sql          # Carga automática dos produtos
├── build.gradle
└── gradlew.bat
```

## Observação

Este backend faz parte do projeto acadêmico **Market** e foi desenvolvido para execução local.
