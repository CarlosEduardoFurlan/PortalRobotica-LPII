# Portal de Robótica IFMS - Campus Campo Grande

Projeto desenvolvido para a disciplina de Desenvolvimento Web, com o objetivo de criar um portal para o Laboratório de Robótica do IFMS - Campus Campo Grande.

## Integrante

- Carlos Eduardo Furlan

## Objetivo

O sistema tem como objetivo divulgar informações do Laboratório de Robótica do IFMS - Campus Campo Grande e permitir o gerenciamento de estudantes, coordenadores, atividades, períodos letivos e participações.

O portal possui uma área pública para consulta das informações e uma área administrativa para manutenção dos dados.

## Tecnologias utilizadas

- Java
- JSP
- Servlets
- JSTL
- JDBC
- Maven
- PostgreSQL
- Apache Tomcat 9
- Bootstrap
- CSS
- HTML

## Funcionalidades

### Área pública

A área pública permite consultar informações relacionadas ao Laboratório de Robótica.

Funcionalidades disponíveis:

- Página inicial do portal
- Página sobre o laboratório
- Listagem de estudantes
- Exibição da minibio dos estudantes
- Exibição da foto dos estudantes
- Exibição das atividades das quais cada estudante participou
- Listagem de atividades
- Exibição dos períodos letivos das atividades
- Página de detalhes de cada atividade
- Exibição da descrição da atividade
- Exibição do tipo da atividade
- Exibição da situação da atividade
- Exibição das datas de início e término
- Exibição dos participantes de cada atividade
- Exibição da função e contribuição dos participantes
- Acesso ao Instagram do Laboratório de Robótica
- Suporte aos idiomas português, inglês, espanhol e francês

### Área administrativa

A área administrativa permite realizar a manutenção dos dados utilizados pelo portal.

Funcionalidades disponíveis:

- Cadastro de estudantes
- Exclusão de estudantes
- Upload de foto dos estudantes
- Cadastro de coordenadores
- Exclusão de coordenadores
- Cadastro de atividades
- Exclusão de atividades
- Cadastro de períodos letivos
- Exclusão de períodos letivos
- Associação de estudantes às atividades
- Exclusão de participações
- Cadastro de atividades com associação a um ou dois períodos letivos
- Exclusão das associações entre atividade e período
- Validações de integridade
- Mensagens de sucesso
- Mensagens de erro
- Internacionalização da interface administrativa

## Estrutura do projeto

O projeto utiliza separação de responsabilidades entre Model, DAO, Servlet e JSP.

```text
portal-robotica/
│
├── pom.xml
├── README.md
│
├── sql/
│   └── portal_robotica.sql
│
└── src/
    └── main/
        │
        ├── java/
        │   └── br/
        │       └── edu/
        │           └── ifms/
        │               └── robotica/
        │                   ├── dao/
        │                   ├── model/
        │                   ├── servlet/
        │                   └── util/
        │
        ├── resources/
        │   ├── messages.properties
        │   ├── messages_pt_BR.properties
        │   ├── messages_en_US.properties
        │   ├── messages_es_ES.properties
        │   └── messages_fr_FR.properties
        │
        └── webapp/
            ├── css/
            │   └── style.css
            │
            ├── img/
            │   └── logo/
            │
            └── WEB-INF/
                └── views/
                    ├── admin/
                    ├── includes/
                    └── public/
```

## Arquitetura

O projeto utiliza a seguinte organização:

```text
Banco de dados
      ↑
     DAO
      ↑
   Servlet
      ↓
     JSP
      ↓
   Usuário
```

### Model

As classes de Model representam os dados utilizados pelo sistema.

Entre os principais modelos estão:

- Estudante
- Coordenador
- Atividade
- Período Letivo
- Participação
- Atividade / Período

### DAO

As classes DAO são responsáveis pela comunicação com o banco de dados.

Os comandos SQL de inserção, consulta e exclusão ficam concentrados nessa camada.

Isso evita colocar comandos SQL diretamente nas páginas JSP ou nos Servlets.

### Servlet

Os Servlets recebem as requisições HTTP enviadas pelo navegador.

Eles são responsáveis por:

- receber os parâmetros dos formulários;
- realizar validações;
- chamar os métodos dos DAOs;
- organizar os dados necessários;
- encaminhar as informações para as páginas JSP;
- realizar redirecionamentos após operações de cadastro e exclusão.

### JSP

As páginas JSP são responsáveis pela apresentação das informações.

O projeto utiliza JSTL e Expression Language para:

- condições;
- repetições;
- exibição de dados;
- internacionalização;
- mensagens de sucesso e erro.

## Banco de dados

O projeto utiliza PostgreSQL.

O banco utilizado pela aplicação é:

```text
portal_robotica
```

O script de criação das tabelas está localizado em:

```text
sql/portal_robotica.sql
```

As principais tabelas utilizadas nesta versão são:

- `estudante`
- `coordenador`
- `atividade`
- `periodo_letivo`
- `participacao`
- `atividade_periodo`

O modelo do banco também possui a tabela `conquista`, preparada para uma etapa futura do projeto.

## Configuração do banco

A conexão com o PostgreSQL é realizada pela classe:

```text
Conexao.java
```

A configuração utilizada durante o desenvolvimento é:

```text
URL: jdbc:postgresql://localhost:5432/portal_robotica
Usuário: {SEU_USUARIO}
Senha: {SUA_SENHA}
```

Caso o PostgreSQL esteja configurado com outro usuário, senha ou porta, os dados devem ser alterados na classe `Conexao.java`.

## Criação do banco de dados

Antes de executar o projeto, crie o banco:

```sql
CREATE DATABASE portal_robotica;
```

Depois, conecte-se ao banco `portal_robotica` e execute:

```text
sql/portal_robotica.sql
```

Esse script cria as tabelas e os relacionamentos necessários para a aplicação.

## Regras de integridade

O sistema possui regras para evitar inconsistências nos dados.

Entre elas:

- o mesmo estudante não pode participar duas vezes da mesma atividade;
- a mesma atividade não pode ser associada duas vezes ao mesmo período letivo;
- a data final de uma atividade não pode ser anterior à data inicial;
- um coordenador que possui atividades associadas não pode ser excluído;
- um período letivo associado a uma atividade não pode ser excluído;
- os relacionamentos devem apontar para registros existentes.

## Tipos de atividade

O sistema utiliza um conjunto controlado de tipos de atividade.

Os valores disponíveis são:

- Projeto
- Estágio
- Tarefa
- Oficina
- Palestra
- Evento
- Competição
- Visita

Internamente, os valores são armazenados de forma padronizada, por exemplo:

```text
projeto
estagio
tarefa
oficina
palestra
evento
competicao
visita
```

A interface apresenta a tradução correspondente ao idioma selecionado.

## Situações das atividades

As atividades podem possuir uma das seguintes situações:

- Planejada
- Em andamento
- Concluída

Internamente, são utilizados os valores:

```text
planejada
em_andamento
concluida
```

A apresentação desses valores é traduzida de acordo com o idioma selecionado.

## Períodos letivos

Os períodos letivos são formados por:

- ano;
- semestre.

Os semestres permitidos são:

```text
1
2
```

Uma atividade pode estar relacionada a mais de um período letivo.

Esse relacionamento é armazenado na tabela:

```text
atividade_periodo
```

## Participações

O relacionamento entre estudantes e atividades é realizado pela tabela:

```text
participacao
```

Além da associação entre estudante e atividade, uma participação pode possuir:

- função;
- descrição da contribuição.

Isso permite registrar o papel desempenhado pelo estudante em cada atividade.

## Upload de fotos dos estudantes

O sistema permite realizar upload de fotos dos estudantes.

No formulário administrativo, o usuário pode selecionar uma imagem do computador.

Os formatos aceitos pela interface são:

- JPEG
- PNG
- WebP

O Servlet recebe a imagem utilizando:

```java
@MultipartConfig
```

e:

```java
request.getPart("foto");
```

A imagem é salva em uma pasta externa ao projeto.

O banco de dados não armazena os bytes da imagem. Ele armazena apenas o nome do arquivo.

Um Servlet específico é utilizado para disponibilizar a imagem ao navegador.

O fluxo funciona da seguinte forma:

```text
Formulário
    ↓
EstudanteServlet
    ↓
Arquivo salvo no computador
    ↓
Nome salvo no banco
    ↓
FotoEstudanteServlet
    ↓
Imagem exibida no navegador
```

## Internacionalização

O portal possui suporte aos seguintes idiomas:

- Português
- Inglês
- Espanhol
- Francês

Os arquivos de tradução ficam em:

```text
src/main/resources/
```

Arquivos utilizados:

```text
messages.properties
messages_pt_BR.properties
messages_en_US.properties
messages_es_ES.properties
messages_fr_FR.properties
```

As páginas JSP utilizam JSTL:

```jsp
<fmt:setLocale value="${sessionScope.idioma}" />
<fmt:setBundle basename="messages" />
```

e as mensagens são recuperadas por meio de:

```jsp
<fmt:message key="chave.da.mensagem" />
```

O idioma escolhido permanece armazenado na sessão durante a navegação.

## Mensagens de sucesso e erro

Após operações realizadas com sucesso, o sistema redireciona o usuário utilizando parâmetros na URL.

Exemplo:

```text
/admin/estudantes?sucesso=cadastro
```

As páginas JSP verificam o parâmetro e exibem a mensagem correspondente.

As mensagens de erro de regras de negócio também utilizam internacionalização.

O Servlet envia uma chave:

```java
request.setAttribute(
    "erro",
    "erro.periodo.duplicado"
);
```

e o JSP apresenta a mensagem no idioma atual:

```jsp
<fmt:message key="${erro}" />
```

## Interface

A interface foi desenvolvida utilizando:

- Bootstrap
- CSS personalizado
- identidade visual inspirada no IFMS

Foram utilizados elementos como:

- cabeçalho institucional;
- menu de navegação;
- cards;
- tabelas;
- formulários;
- botões;
- mensagens de alerta;
- layout responsivo.

## Como executar o projeto

### 1. Pré-requisitos

É necessário ter instalado:

- Java
- Eclipse IDE
- Maven
- PostgreSQL
- Apache Tomcat 9

### 2. Criar o banco

No PostgreSQL:

```sql
CREATE DATABASE portal_robotica;
```

### 3. Criar as tabelas

Execute:

```text
sql/portal_robotica.sql
```

no banco `portal_robotica`.

### 4. Configurar a conexão

Confira os dados da classe:

```text
Conexao.java
```

e ajuste usuário, senha ou porta caso necessário.

### 5. Importar o projeto

No Eclipse:

```text
File
→ Import
→ Existing Maven Projects
```

Selecione a pasta do projeto.

### 6. Configurar o Tomcat

Configure o Apache Tomcat 9 no Eclipse.

Depois adicione o projeto ao servidor.

### 7. Executar

Inicie o Tomcat e acesse:

```text
http://localhost:8080/portal-robotica/
```

## Páginas públicas

As principais páginas públicas são:

```text
/
/sobre
/estudantes
/atividades
/atividade?id=ID
```

## Área administrativa

As principais rotas administrativas são:

```text
/admin/estudantes
/admin/coordenadores
/admin/atividades
/admin/periodos
/admin/participacoes
```

## Testes realizados

Durante o desenvolvimento foram testados os seguintes cenários:

- cadastro de estudante;
- upload de foto;
- exclusão de estudante;
- cadastro de coordenador;
- exclusão de coordenador;
- bloqueio da exclusão de coordenador associado a atividade;
- cadastro de atividade;
- exclusão de atividade;
- validação das datas da atividade;
- cadastro de período letivo;
- bloqueio de período duplicado;
- bloqueio da exclusão de período associado;
- associação de estudante a atividade;
- bloqueio de participação duplicada;
- exclusão de participação;
- cadastro de atividade com associação a um período letivo;
- cadastro de atividade com associação a dois períodos letivos;
- prevenção da repetição do mesmo período no cadastro da atividade;
- troca entre português, inglês, espanhol e francês;
- exibição pública de estudantes;
- exibição das atividades dos estudantes;
- exibição pública das atividades;
- exibição dos períodos das atividades;
- exibição dos participantes no detalhe da atividade.

## Observações

O projeto foi desenvolvido para execução local em ambiente acadêmico.

Nesta versão não há autenticação de usuários.

A área administrativa está disponível sem login para fins de demonstração e avaliação acadêmica.

Funcionalidades como alteração de registros, autenticação e gerenciamento de conquistas podem ser implementadas em etapas futuras.

## Autor

**Carlos Eduardo Furlan**

IFMS - Campus Campo Grande