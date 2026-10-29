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
