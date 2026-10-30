# PROMPTS.md — Uso de Inteligência Artificial

Registro exigido pelo edital (item 8, observação 5): cada prompt usado, a fonte e o que o grupo fez com a resposta.

- **Projeto:** ControleDePonto — Banco de Dados, FAESA 2026/2, Prof. Howard Roatti
- **Observação:** os prompts abaixo estão transcritos como foram enviados.
- **Organização:** a Parte 1 reúne os prompts usados no início do projeto (conexão, controllers e testes). A Parte 2 reúne os prompts da conversa com o Claude, usada para completar o CRUD, o README e este arquivo.

> **Antes de entregar, o grupo deve:**
> 1. preencher todos os campos marcados com `[preencher]` ou `[confirmar]` e apagar as marcas;
> 2. apagar este bloco de aviso.

---

# Parte 1 — Prompts do início do projeto

Em todos os registros desta parte, a data e o integrante que usou o prompt estão pendentes de preenchimento.

### Prompt A — Criação da classe de conexão com o banco de dados

- **Data:** [preencher]
- **Fonte (ferramenta de IA):** ChatGPT (OpenAI)
- **Integrante:** [preencher]
- **Prompt:**

  > Estou desenvolvendo um projeto em Java chamado ControleDePonto para a faculdade. Crie uma classe Conexao.java responsável por conectar a aplicação a um banco de dados MySQL chamado controle_ponto, utilizando JDBC. Organize o código em um pacote chamado com.controleponto.utils e crie um método estático para estabelecer a conexão com o banco. Explique como configurar a URL, o usuário e a senha do banco de dados.

- **O que o grupo fez:** a resposta serviu como base para implementar a conexão entre a aplicação Java e o banco de dados MySQL.

### Prompt B — Configuração segura da conexão

- **Data:** [preencher]
- **Fonte (ferramenta de IA):** ChatGPT (OpenAI)
- **Integrante:** [preencher]
- **Prompt:**

  > Melhore a classe Conexao.java para obter a senha do banco de dados por meio de uma variável de ambiente, em vez de deixar a senha diretamente escrita no código. Utilize JDBC e trate possíveis erros de conexão com SQLException. Explique como configurar a variável de ambiente no Windows.

- **O que o grupo fez:** a orientação foi utilizada para ajustar a classe de conexão, evitando manter a senha diretamente no código-fonte e permitindo tratar erros durante a conexão.

### Prompt C — Teste de conexão com o banco de dados

- **Data:** [preencher]
- **Fonte (ferramenta de IA):** ChatGPT (OpenAI)
- **Integrante:** [preencher]
- **Prompt:**

  > Crie um teste simples em Java para verificar se a aplicação consegue se conectar ao banco de dados MySQL controle_ponto utilizando a classe Conexao.java. Mostre uma mensagem informando se a conexão foi estabelecida ou se ocorreu algum erro. Explique como executar o teste na IDE Eclipse.

- **O que o grupo fez:** o exemplo serviu para verificar a comunicação entre o Java e o MySQL antes de testar as demais funcionalidades do sistema.

### Prompt D — Criação do controller de funcionários

- **Data:** [preencher]
- **Fonte (ferramenta de IA):** ChatGPT (OpenAI)
- **Integrante:** [preencher]
- **Prompt:**

  > Estou desenvolvendo um sistema de controle de ponto em Java com MySQL e JDBC. Crie uma classe FuncionarioController.java, no pacote com.controleponto.controller, para realizar operações de cadastro, listagem, atualização e exclusão de funcionários. Considere a tabela funcionario, que possui os campos id, nome, cpf, cargo e salario. Utilize a classe Conexao.java para acessar o banco de dados e trate erros que possam ocorrer durante as consultas e operações.

- **O que o grupo fez:** a resposta foi utilizada como referência para implementar as operações de gerenciamento de funcionários e integrar o código Java com a tabela correspondente no banco de dados.

### Prompt E — Criação do controller de registros de ponto

- **Data:** [preencher]
- **Fonte (ferramenta de IA):** ChatGPT (OpenAI)
- **Integrante:** [preencher]
- **Prompt:**

  > Crie uma classe RegistroPontoController.java, no pacote com.controleponto.controller, para cadastrar e listar registros de ponto de funcionários. A tabela registro_ponto possui os campos id, funcionario_id, data_hora e tipo. Utilize Java, JDBC e a classe Conexao.java. Considere a chave estrangeira que relaciona os registros de ponto aos funcionários e trate possíveis erros ao inserir ou consultar os registros.

- **O que o grupo fez:** a orientação foi utilizada para implementar o gerenciamento dos registros de ponto, considerando o relacionamento entre as tabelas funcionario e registro_ponto.

### Prompt F — Testes e correção de erros

- **Data:** [preencher]
- **Fonte (ferramenta de IA):** ChatGPT (OpenAI)
- **Integrante:** [preencher]
- **Prompt:**

  > Estou testando meu projeto ControleDePonto, desenvolvido em Java com JDBC e MySQL. Ajude-me a identificar e corrigir erros nas operações de cadastro, listagem, atualização e exclusão de funcionários, no registro de pontos e nos relatórios SQL. Explique as causas dos erros e proponha correções compatíveis com a estrutura atual do projeto.

- **O que o grupo fez:** as orientações foram utilizadas durante os testes do sistema, para compreender mensagens de erro, verificar o relacionamento entre as tabelas e corrigir problemas encontrados durante a execução.

---

# Parte 2 — Conversa com o Claude (completar CRUD, README e PROMPTS.md)

- **Ferramenta:** Claude (Anthropic), pelo aplicativo claude.ai
- **Data:** 30/10/2026

### Prompt 1 — Pedido para completar o item 3 (CRUD)

- **Anexos enviados:** lista de tarefas do projeto (itens 1 a 6) e arquivo ZIP com a página de avaliações da disciplina
- **Prompt:**

  > visando os anexos acima, faça oque falta do item 3 cadastro consulta, atualização e exclusão.
  >
  > temos o link do github com oque ja foi feito do trabalho, o edital falando oque precisa e uma lista com o que ja foi feito e do que precisa ser feito, vamos por parte completando oque falta do item 3

- **Resposta:** a IA não conseguiu acessar o repositório (na época privado) e o ZIP continha apenas a tabela de datas das avaliações, sem o edital. Pediu o código-fonte e o texto do edital.
- **O que o grupo fez:** enviou o edital em PDF e tornou o repositório público (ver Prompt 2).

### Prompt 2 — Envio do edital e do repositório público

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

- **Prompt:**

  > faça o readme para subir pro github

- **Resposta:** `README.md` com descrição do sistema, funcionalidades, modelo de dados, estrutura de pastas e passo a passo para compilar e executar no Linux (banco, usuário, variável `DB_SENHA`, driver JDBC), mais uma tabela de problemas comuns. A IA testou os comandos de compilação e execução com o driver MariaDB.
- **O que o grupo fez:** [confirmar] revisou o texto, testou os comandos na VM Linux, preencheu o link do vídeo e subiu o arquivo no repositório.

### Prompt 4 — Criação do PROMPTS.md

- **Prompt:**

  > sim, faça o prompts.md

- **Resposta:** a primeira versão deste arquivo.
- **O que o grupo fez:** [confirmar] revisou o conteúdo e subiu o arquivo no repositório.

### Prompt 5 — Inclusão dos prompts do início do projeto

- **Prompt:**

  > meu amigo me mandou os prompts que ele usou para fazer os codigos, preciso enviar para voce para compor no promptsmd?

  Em seguida, o grupo enviou os seis prompts da Parte 1, com a descrição de como cada resposta foi usada.
- **Resposta:** esta versão do arquivo, com a Parte 1 incluída e a numeração reorganizada.
- **O que o grupo fez:** [confirmar] revisou o conteúdo e subiu o arquivo no repositório.
