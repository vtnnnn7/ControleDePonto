-- Projeto: ControleDePonto
-- Banco de dados: MySQL
-- Cria o banco e as tabelas usadas pelo sistema Java.

CREATE DATABASE IF NOT EXISTS controle_ponto
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE controle_ponto;

CREATE TABLE IF NOT EXISTS funcionario (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    cargo VARCHAR(60) NOT NULL,
    salario DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_funcionario_cpf (cpf)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS registro_ponto (
    id INT NOT NULL AUTO_INCREMENT,
    funcionario_id INT NOT NULL,
    data_hora DATETIME NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_registro_ponto_funcionario
        FOREIGN KEY (funcionario_id)
        REFERENCES funcionario (id)
        ON UPDATE CASCADE
        ON DELETE RESTRICT
) ENGINE = InnoDB;

-- Consultas de conferência:
-- SELECT * FROM funcionario;
-- SELECT * FROM registro_ponto;
