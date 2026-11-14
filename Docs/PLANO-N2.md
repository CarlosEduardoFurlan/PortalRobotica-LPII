# Plano de implementação da N2

## Situação atual (N1)

- Projeto Maven `war`, Java 17, Servlet API 4 (`javax.servlet`), JSP/JSTL, JDBC e PostgreSQL, compatível com Tomcat 9.
- Estrutura Web preservada em `src/main/webapp`, com JSPs públicas e administrativas em `WEB-INF/views`.
- Models existentes: `Estudante`, `Coordenador`, `Atividade`, `PeriodoLetivo`, `Participacao`, `AtividadePeriodo` e `Conquista`.
- DAOs existentes para todas as entidades exceto `Conquista`; operações concentram-se em cadastro, listagem e exclusão.
- Servlets públicos para sobre, estudantes, atividades e detalhe de atividade; servlets administrativos sem autenticação.
- Cadastro de atividade já associa até dois períodos, mas por conexões separadas; edição transacional ainda não existe.
- Upload de foto de estudante usa pasta externa em `user.home/portal-robotica/uploads/estudantes` e servlet de leitura protegido contra caminho relativo.
- Internacionalização existente em PT/EN/ES/FR por JSTL e arquivos `messages*.properties`.
- `Conexao` usa `DriverManager` e contém credenciais locais no código; precisa migrar para `DataSource` JNDI.
- SQL de instalação contém as tabelas da N1 e `conquista`, mas ainda não possui credenciais, status de conta nem tokens.
- Metadados do Eclipse já existem e têm alterações locais do usuário; serão preservados sem modificações.

## Alterações necessárias

1. Migrar a conexão para o `DataSource` JNDI `java:comp/env/jdbc/PortalRoboticaDB`, mantendo DAOs JDBC.
2. Evoluir SQL e models com e-mail, hash bcrypt e status ativo; criar migração N2 sem perda de dados.
3. Adicionar bcrypt, autenticação de coordenador, sessão mínima, logout e filtro de `/admin/*` com revalidação da conta.
4. Evoluir estudantes e coordenadores para consulta, alteração, inativação e reativação, com cadastro de senha inicial e e-mail único.
5. Implementar edição completa de atividade e atualização de períodos na mesma conexão/transação.
6. Implementar edição de participação mantendo sua chave composta.
7. Implementar CRUD de conquistas e exibição no detalhe público.
8. Implementar alteração pública de senha para estudante/coordenador.
9. Implementar solicitação, token seguro, e-mail configurável e redefinição transacional de senha.
10. Implementar relatório administrativo por período, montando atividades, participantes e conquistas no Java sem duplicação visual.
11. Atualizar as quatro traduções, navegação e páginas JSP preservando Bootstrap, CSS e identidade atual.
12. Atualizar README e criar checklist manual completo.
13. Executar build Maven e verificações estáticas de JSP, SQL, rotas, imports, segurança e ausência de `jakarta.*`.

## Arquivos a modificar

- `pom.xml`
- `README.md`
- `sql/portal_robotica.sql`
- Models, DAOs e Servlets existentes em `src/main/java/br/edu/ifms/robotica`
- `src/main/java/br/edu/ifms/robotica/util/Conexao.java`
- JSPs existentes em `src/main/webapp/WEB-INF/views`
- `src/main/webapp/WEB-INF/web.xml`
- `src/main/resources/messages*.properties`
- `src/main/webapp/css/style.css` somente se necessário para integração visual das novas telas.

## Arquivos a criar

- `sql/portal_robotica_n2_migracao.sql`
- Classes de segurança, autenticação, senha, token, e-mail, conquista e relatório em seus pacotes atuais/novos.
- JSPs de login, alteração/recuperação/redefinição de senha, conquistas e relatório.
- `src/main/resources/email.properties.example` (sem segredos).
- `src/main/webapp/META-INF/context.xml` com referência de recurso associada ao projeto, sem credenciais.
- `Docs/CHECKLIST-TESTES-N2.md`

## Ordem de implementação

1. Infraestrutura: dependências, JNDI, SQL final/migração, models e utilitários de segurança.
2. Autenticação, sessão, filtro e navegação protegida.
3. CRUD/status de estudantes e coordenadores.
4. Edição transacional de atividades e edição de participações.
5. Conquistas administrativas e públicas.
6. Alteração e recuperação de senha com e-mail de teste.
7. Relatório por período.
8. Internacionalização e acabamento visual.
9. Documentação, checklist, build e revisão final.
