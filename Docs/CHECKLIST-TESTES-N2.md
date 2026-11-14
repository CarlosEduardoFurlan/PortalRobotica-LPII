# Checklist de testes manuais da N2

Antes dos testes: executar a migração, configurar o JNDI no Tomcat 9 do Eclipse, configurar o primeiro coordenador e iniciar o projeto pelo painel **Servers**.

## Autenticação

- [ ] Login válido redireciona para a administração.
- [ ] Senha inválida e e-mail inexistente apresentam a mesma mensagem genérica.
- [ ] Coordenador inativo não autentica.
- [ ] Acesso direto à administração sem sessão redireciona ao login.
- [ ] Logout, sessão expirada e botão voltar não reabrem páginas protegidas.
- [ ] Inativar o coordenador em outra sessão encerra seu próximo acesso.

## Estudantes e coordenadores

- [ ] Cadastrar estudante com dados, foto, e-mail e senha inicial.
- [ ] Rejeitar e-mail vazio/duplicado e senha menor que 8 caracteres.
- [ ] Alterar sem exibir nem alterar senha.
- [ ] Inativar e reativar estudante, preservando participações.
- [ ] Somente estudantes ativos aparecem na área pública e em novos vínculos.
- [ ] Cadastrar, alterar, inativar e reativar coordenador.
- [ ] Atividades permanecem ligadas ao coordenador inativo.

## Atividades e transações

- [ ] Cadastrar com um e dois períodos.
- [ ] Alterar todos os campos e períodos.
- [ ] Rejeitar tipo/situação inválidos, data inválida e períodos repetidos.
- [ ] Excluir e conferir vínculos em cascata.
- [ ] Commit normal altera atividade e períodos.
- [ ] Rollback: pelo inspetor do navegador, trocar um periodoId por ID inexistente; após a falha da FK, confirmar no banco que atividade e períodos antigos não mudaram.

## Participações e conquistas

- [ ] Criar, editar e remover participação.
- [ ] Impedir chave estudante/atividade duplicada.
- [ ] Manter estudante e atividade somente leitura na edição.
- [ ] Criar, alterar e excluir conquista.
- [ ] Rejeitar atividade inexistente e mostrar conquistas no detalhe público.

## Senhas e recuperação

- [ ] Alterar senha de estudante/coordenador com senha atual correta.
- [ ] Rejeitar senha atual incorreta, conta inativa, senha curta e confirmação diferente.
- [ ] Confirmar bcrypt no banco, sem texto puro.
- [ ] Solicitar recuperação para endereço existente e inexistente; a resposta é idêntica.
- [ ] Usar token válido recebido no SMTP de teste.
- [ ] Rejeitar token inválido, expirado e já usado.
- [ ] Confirmar que senha e usado_em mudam juntos na transação.

## Relatório, idiomas e build

- [ ] Período com atividades e período vazio.
- [ ] Atividade sem estudantes, sem conquistas e com ambos.
- [ ] Cada atividade aparece exatamente uma vez.
- [ ] Testar novos fluxos em PT, EN, ES e FR.
- [ ] Maven > Update Project sem erros e aba Problems limpa.
- [ ] mvn clean package gera o WAR.
- [ ] Tomcat 9 inicia em Servers e abre o portal.
