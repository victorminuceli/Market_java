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

## Executar o backend

1. Abra a pasta:

```text
Market_java/market
```
como projeto no IntelliJ IDEA.

2. Aguarde o IntelliJ importar o Gradle e baixar as dependências.

3. Confirme que o projeto está utilizando **Java 17**.

4. Abra:

```text
src/main/java/com/example/market/MarketApplication.java
```

5. Clique no botão ▶ ao lado do método `main` e execute `MarketApplication`.

6. Na primeira execução, o IntelliJ poderá criar automaticamente uma configuração para a aplicação.

7. Abra **Run → Edit Configurations** e, na configuração `MarketApplication`, configure a variável de ambiente:

```text
DB_PASSWORD=SUA_SENHA_DO_MYSQL
```

Substitua `SUA_SENHA_DO_MYSQL` pela senha real do MySQL.

> A senha não deve ser colocada diretamente no `application.properties` nem enviada para o GitHub.

> **Exemplo:** uma imagem da configuração do backend no IntelliJ está disponível no repositório [Market_java](https://github.com/victorminuceli/Market_java), na pasta `docs`: [ver imagem](https://github.com/victorminuceli/Market_java/blob/main/market/docs/intellij-config.png).

8. Execute novamente `MarketApplication`.

Se algum campo estiver oculto, procure-o em **Modify options**.

### Pelo PowerShell

Como alternativa ao IntelliJ, abra o PowerShell na pasta do backend:

```powershell
cd .\Market_java\market
```

Configure a senha do MySQL para esse terminal:

```powershell
$env:DB_PASSWORD = 'SUA_SENHA_DO_MYSQL'
```

Depois execute:

```powershell
.\gradlew.bat bootRun
```

A variável `DB_PASSWORD` vale somente para esse terminal.

Use apenas uma forma de execução por vez para evitar conflito na porta `8080`.

### Inicialização automática do banco

Na primeira inicialização, o Hibernate cria ou atualiza as tabelas porque o projeto utiliza:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Depois disso, o Spring executa automaticamente o:

```text
src/main/resources/data.sql
```

O arquivo contém os produtos iniciais do catálogo.

As configurações responsáveis pela execução são:

```properties
spring.jpa.defer-datasource-initialization=true
spring.sql.init.mode=always
```

Uma inicialização bem-sucedida mostra mensagens semelhantes a:

```text
Tomcat started on port 8080
Started MarketApplication
```

Teste a API em:

```text
http://localhost:8080/produtos
```

### Sobre o `data.sql`

O `data.sql` utiliza `INSERT IGNORE` para evitar erros de chave duplicada quando o backend é iniciado novamente.

Isso significa que:

- na primeira execução, os produtos são inseridos;
- nas execuções seguintes, produtos que já existem são ignorados;
- se um dos produtos iniciais for excluído do banco e o backend for reiniciado, ele poderá ser inserido novamente pelo `data.sql`.

Esse comportamento é intencional para manter a carga inicial do catálogo automatizada.

Atualmente, o CRUD de produtos não é disponibilizado no frontend, pois o usuário do sistema é tratado como cliente do supermercado. Futuramente, caso necessário, o sistema poderá separar os perfis de cliente e funcionário, permitindo o gerenciamento de produtos por funcionários.

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
