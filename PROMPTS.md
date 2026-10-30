# PROMPTS.md — Uso de Inteligência Artificial

Registro exigido pelo edital (item 8, observação 5): cada prompt usado, a fonte e o que o grupo fez com a resposta.

- **Ferramenta:** Claude (Anthropic), pelo aplicativo claude.ai
- **Projeto:** ControleDePonto — Banco de Dados, FAESA 2026/2, Prof. Howard Roatti
- **Observação:** os prompts abaixo estão transcritos como foram enviados.

> **Antes de entregar, o grupo deve:**
> 1. conferir a coluna "O que o grupo fez" de cada registro e corrigir o que não corresponder ao que foi feito de fato;
> 2. incluir na seção "Registros anteriores" os prompts de conversas anteriores sobre este projeto, caso existam.

---

## Registros

### Prompt 1 — Pedido para completar o item 3 (CRUD)

- **Data:** 30/10/2026
- **Fonte:** Claude (claude.ai)
- **Anexos enviados:** lista de tarefas do projeto (itens 1 a 6) e arquivo ZIP com a página de avaliações da disciplina
- **Prompt:**

  > visando os anexos acima, faça oque falta do item 3 cadastro consulta, atualização e exclusão.
  >
  > temos o link do github com oque ja foi feito do trabalho, o edital falando oque precisa e uma lista com o que ja foi feito e do que precisa ser feito, vamos por parte completando oque falta do item 3

- **Resposta:** a IA não conseguiu acessar o repositório (na época privado) e o ZIP continha apenas a tabela de datas das avaliações, sem o edital. Pediu o código-fonte e o texto do edital.
- **O que o grupo fez:** enviou o edital em PDF e tornou o repositório público (ver Prompt 2).

### Prompt 2 — Envio do edital e do repositório público

- **Data:** 30/10/2026
- **Fonte:** Claude (claude.ai)
- **Anexo enviado:** `BD_trab.pdf` (edital do trabalho)
- **Prompt:**

  > vou reenviar o link do github que agora esta publico para que possa ser realizado os topicos que ainda faltam, o professor ja esta como colaborador do projeto. https://github.com/vtnnnn7/ControleDePonto
  >
  > fora isso estarei enviando os anexos para visualização do que deve ser feito no trabalho

- **Resposta:** a IA leu o repositório e o edital e implementou o que faltava do item 3, em quatro arquivos:
  - `Main.java`: menu principal no formato do edital (consultar, inserir, remover, atualizar, relatórios e sair), com submenu de entidades e pergunta "deseja continuar?" ao final de cada operação;
  - `FuncionarioController.java`: busca por id, atualização com Enter mantendo o valor atual, contagem de pontos vinculados e exclusão do funcionário junto com seus pontos em transação;
  - `RegistroPontoController.java`: listagem com o nome do funcionário, atualização (que não existia), busca por id e exclusão;
  - `utils/Entrada.java` (novo): leitura de dados do console com validação.

  A IA compilou o código e o testou em um MariaDB local, usando o script `script_controle_ponto.sql` do projeto, com uma sessão que passou por inserção, consulta, atualização, exclusão e relatórios.
- **O que o grupo fez:** [confirmar] copiou os quatro arquivos para o projeto, testou no MySQL e na máquina virtual Linux da disciplina e fez o commit.

### Prompt 3 — README

- **Data:** 30/10/2026
- **Fonte:** Claude (claude.ai)
- **Prompt:**

  > faça o readme para subir pro github

- **Resposta:** `README.md` com descrição do sistema, funcionalidades, modelo de dados, estrutura de pastas e passo a passo para compilar e executar no Linux (banco, usuário, variável `DB_SENHA`, driver JDBC), mais uma tabela de problemas comuns. A IA testou os comandos de compilação e execução com o driver MariaDB.
- **O que o grupo fez:** [confirmar] revisou o texto, testou os comandos na VM Linux, preencheu o link do vídeo e subiu o arquivo no repositório.

### Prompt 4 — Este arquivo

- **Data:** 30/10/2026
- **Fonte:** Claude (claude.ai)
- **Prompt:**

  > sim, faça o prompts.md

- **Resposta:** este arquivo, `PROMPTS.md`.
- **O que o grupo fez:** [confirmar] revisou o conteúdo, completou a seção "Registros anteriores" e subiu o arquivo no repositório.

---

## Registros anteriores

> O histórico do projeto indica que a estrutura inicial, a conexão com o MySQL e os primeiros cadastros foram feitos antes desta conversa. Se IA foi usada nessas etapas, registre aqui cada prompt no mesmo formato acima (data, fonte, prompt, resposta, o que o grupo fez). Se não foi usada, apague esta seção.

### Prompt A — [preencher]

- **Data:**
- **Fonte:**
- **Prompt:**
- **Resposta:**
- **O que o grupo fez:**
