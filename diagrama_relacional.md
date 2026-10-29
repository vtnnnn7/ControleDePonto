erDiagram
    FUNCIONARIO ||--o{ REGISTRO_PONTO : possui

    FUNCIONARIO {
        INT id PK
        VARCHAR nome
        VARCHAR cpf UK
        VARCHAR cargo
        DECIMAL salario
    }

    REGISTRO_PONTO {
        INT id PK
        INT funcionario_id FK
        DATETIME data_hora
        VARCHAR tipo
    }
<img width="588" height="1116" alt="mermaid-diagram" src="https://github.com/user-attachments/assets/a0be5525-bdaf-49fd-a4a0-277bb5235da4" />
