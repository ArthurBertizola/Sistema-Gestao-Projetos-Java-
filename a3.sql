USE a3;
CREATE TABLE usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_completo VARCHAR(255) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil VARCHAR(50) NOT NULL,
    cargo VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Criando a tabela de equipes
CREATE TABLE equipes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

-- Tabela para relacionar usuários e equipes (relação N:N)
CREATE TABLE equipe_membros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    equipe_id INT NOT NULL,
    usuario_id INT NOT NULL,
    FOREIGN KEY (equipe_id) REFERENCES equipes(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

-- Criando a tabela de projetos
CREATE TABLE projetos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    status VARCHAR(50) NOT NULL
);

-- Tabela para relacionar projetos e equipes (relação N:N)
CREATE TABLE projeto_equipes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    projeto_id INT NOT NULL,
    equipe_id INT NOT NULL,
    FOREIGN KEY (projeto_id) REFERENCES projetos(id),
    FOREIGN KEY (equipe_id) REFERENCES equipes(id)
);

-- Criando a tabela de tarefas
CREATE TABLE tarefas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao TEXT NOT NULL,
    data_de_vencimento DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    responsavel VARCHAR(255), -- Em um sistema real, seria um FOREIGN KEY para a tabela de usuários
    projeto_id INT NOT NULL,
    FOREIGN KEY (projeto_id) REFERENCES projetos(id)
);