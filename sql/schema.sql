-- =====================================================================
-- Banco de Dados: Sistema Psicopedagogico Escolar
-- Compatível com MySQL 5.7+/8.0. Para PostgreSQL, veja notas no final.
-- =====================================================================

CREATE DATABASE IF NOT EXISTS psicopedagogico
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE psicopedagogico;

-- ---------------------------------------------------------------------
-- Tabela de usuarios do sistema (login)
-- ---------------------------------------------------------------------
CREATE TABLE usuario (
    id      INT AUTO_INCREMENT PRIMARY KEY,
    nome    VARCHAR(150) NOT NULL,
    email   VARCHAR(150) NOT NULL UNIQUE,
    senha   VARCHAR(100) NOT NULL,
    perfil  VARCHAR(20)  NOT NULL,
    CONSTRAINT chk_perfil CHECK (perfil IN ('ADMIN_PSICO', 'PROFESSOR', 'RESPONSAVEL'))
);

-- ---------------------------------------------------------------------
-- Tabela de alunos
-- id em VARCHAR(36) para armazenar UUID gerado em Java
-- ---------------------------------------------------------------------
CREATE TABLE aluno (
    id                  VARCHAR(36) PRIMARY KEY,
    nome                VARCHAR(150) NOT NULL,
    data_nascimento     DATE NOT NULL,
    ciclo               VARCHAR(20) NOT NULL,
    status              VARCHAR(10) NOT NULL,
    anotacoes_clinicas  TEXT NULL,
    CONSTRAINT chk_ciclo  CHECK (ciclo  IN ('INFANTIL', 'FUNDAMENTAL_1', 'FUNDAMENTAL_2')),
    CONSTRAINT chk_status CHECK (status IN ('ATIVO', 'INATIVO'))
);

-- ---------------------------------------------------------------------
-- Dados de teste
-- ---------------------------------------------------------------------

-- Um usuario de cada perfil (senha em texto simples, apenas para estudo)
INSERT INTO usuario (nome, email, senha, perfil) VALUES
('Maria Souza',  'psicologa@escola.com',  '123456', 'ADMIN_PSICO'),
('Joao Pereira', 'professor@escola.com',  '123456', 'PROFESSOR'),
('Ana Lima',     'responsavel@escola.com','123456', 'RESPONSAVEL');

-- Dois alunos de exemplo
INSERT INTO aluno (id, nome, data_nascimento, ciclo, status, anotacoes_clinicas) VALUES
(UUID(), 'Pedro Henrique Santos', '2016-03-12', 'INFANTIL',      'ATIVO',
    'Apresenta dificuldade de atencao em sala de aula. Acompanhamento quinzenal recomendado.'),
(UUID(), 'Larissa Costa Almeida', '2013-08-25', 'FUNDAMENTAL_1', 'ATIVO', NULL);

-- =====================================================================
-- NOTAS PARA POSTGRESQL
-- =====================================================================
-- 1. Troque "AUTO_INCREMENT" por "SERIAL" na tabela usuario:
--      id SERIAL PRIMARY KEY
-- 2. Troque UUID() (função do MySQL) por gen_random_uuid()::text
--    (requer a extensao pgcrypto: CREATE EXTENSION IF NOT EXISTS pgcrypto;)
--    ou simplesmente gere o UUID em Java antes do INSERT (é o que o
--    AlunoDAO.java já faz - o script SQL usa UUID() só para os dados de teste).
-- 3. Remova "CREATE DATABASE" e "USE" (no Postgres o banco é criado
--    separadamente com createdb ou via pgAdmin, e a conexão já aponta
--    para ele).
-- =====================================================================
