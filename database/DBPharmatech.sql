CREATE DATABASE DBPharmatech;

USE DBPharmatech;

CREATE TABLE tbUsuarios (
    cpf VARCHAR(14) NOT NULL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    tipoDeUsuario VARCHAR(20) NOT NULL
);

CREATE TABLE tbClientes (
    cpf VARCHAR(14) NOT NULL,
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FOREIGN KEY(cpf) REFERENCES tbUsuarios(cpf)  
);

CREATE TABLE tbFarmaceuticos (
    cpf VARCHAR(14) NOT NULL,
    id INT NOT NULL NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FOREIGN KEY(cpf) REFERENCES tbUsuarios(cpf)  
);

CREATE TABLE tbAlmoxerifes (
    cpf VARCHAR(14) NOT NULL,
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FOREIGN KEY(cpf) REFERENCES tbUsuarios(cpf)  
);

CREATE TABLE tbGerentes (
    cpf VARCHAR(14) NOT NULL,
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FOREIGN KEY(cpf) REFERENCES tbUsuarios(cpf)
);

CREATE TABLE tbMedicamentos (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    dosagem VARCHAR(50) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    imagemDoMedicamento VARCHAR(250) NOT NULL,
    preco DOUBLE(5, 2) NOT NULL
);

INSERT INTO tbUsuarios VALUES 
    ('123.456.789-10', 'Retro', '123', '+5511943123154', 'Rua Paulista 10', 'Cliente'),
    ('987.654.321-01', 'Default', '456', '+5511242154212', 'Rua Arnival 31', 'Farmaceutico'),
    ('321.212.341-12', 'Reginaldo', '789', '+551192491941', 'Rua Condor 21', 'Almoxerife'),
    ('941.321.512-04', 'Lineuzinho', '012', '+551124124293', 'Rua Eldorado 54', 'Gerente');

INSERT INTO tbClientes (CPF) VALUES ('123.456.789-10');

INSERT INTO tbFarmaceuticos (CPF) VALUES ('987.654.321-01');

INSERT INTO tbAlmoxerifes (CPF) VALUES ('321.212.341-12');

INSERT INTO tbGerentes (CPF) VALUES ('941.321.512-04');
