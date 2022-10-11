# Exercicio 2 -  26/09/2022
DROP DATABASE IF EXISTS biblioteca;
CREATE DATABASE biblioteca;
USE biblioteca;

DROP TABLE IF EXISTS `Categoria`;
CREATE TABLE `Categoria` (
`Descricao` VARCHAR(50) NOT NULL,
PRIMARY KEY (Descricao)
);

DROP TABLE IF EXISTS `Paises`;
CREATE TABLE `Paises` (
`Nome` VARCHAR(50) NOT NULL,
PRIMARY KEY (Nome)
);

DROP TABLE IF EXISTS `Editora`;
CREATE TABLE `Editora` (
`Nome` VARCHAR(50) NOT NULL,
PRIMARY KEY (Nome)
);

DROP TABLE IF EXISTS `Autores`;
CREATE TABLE `Autores`(
`Nome` VARCHAR(50) NOT NULL,
`Nacionalidade` INT(5) NOT NULL,
PRIMARY KEY (Nome)
);

DROP TABLE IF EXISTS `Livros`;
CREATE TABLE `Livros` (
`ISBN` VARCHAR(50) NOT NULL,
`Titulo` VARCHAR(50) NOT NULL,
`Editora` VARCHAR(50) NOT NULL,
`Ano` INT(4) NOT NULL,
`Autor` VARCHAR(50)  NOT NULL,
`Nacionalidade` VARCHAR(50)  NOT NULL,
`Categoria` VARCHAR(50) NOT NULL,
PRIMARY KEY (ISBN),
CONSTRAINT Fk_Edit FOREIGN KEY (Editora) REFERENCES Editora(Nome),
CONSTRAINT Fk_Aut FOREIGN KEY (Autor) REFERENCES Autores(Nome),
CONSTRAINT Fk_Cat FOREIGN KEY (Categoria) REFERENCES Categoria(Descricao),
CONSTRAINT Fk_Nac FOREIGN KEY (Nacionalidade) REFERENCES Paises(Nome)
);


INSERT INTO Categoria
(`Descricao`)
VALUES
("Literatura Juvenil"),
("Ficção Científica"),
("Humor");

INSERT INTO Editora
(`Nome`)
VALUES
("Rocco"),
("Wmf Martins Fontes"),
("Belas Letras"),
("Matrix"),
("Casa da Palavra");

INSERT INTO Paises
(`Nome`)
VALUES
("Inglaterra"),
("Brasil");

INSERT INTO Autores
(`Nome`,`Nacionalidade`)
VALUES
("J K Rowling","Inglaterra"),
("Clive Staples Lewis","Inglaterra"),
("Affonso Solano","Brasil"),
("Marcos Piangers","Brasil"),
("Ciro Botelho - Tiririca","Brasil"),
("Bianca Moi","Inglaterra");

INSERT INTO Livros
(`ISBN`,`Titulo`,`Ano`,`Editora`,`Categoria`,`Autor`,`Nacionalidade`)
VALUES
('8532511015', "Harry Potter e A Pedra Filosofal", 2000, "Rocco", "Literatura Juvenil", "J K Rowling","Inglaterra"),
('970578270698', "As Cronicas de Narnia", 2009, "Wmf Martins Fontes", "Literatura Juvenil", "Clive Staples Lewis","Inglaterra"),
('9788577343348', "O Espadachim de Carvão", 2013, "Casa da Palavra", "Ficção Científica", "Affonso Solano","Brasil"),
('9788581742458', "O Papai É Pop", 2015, "Belas Letras", "Humor", "Marcos Piangers","Brasil"),
('970582302026', "Pior Que Tá Não Fica", 2015, "Matrix", "Humor", "Ciro Botelho - Tiririca","Brasil"),
('9788577345670', "Garota Desdobrável", 2015, "Casa da Palavra", "Literatura Juvenil", "Bianca Moi","Brasil"),
('8532512062', "Harry Potter e o prisioneiro de Azkaban", 2000, "Rocco", "Literatura Juvenil", "J K Rowling","Inglaterra");

