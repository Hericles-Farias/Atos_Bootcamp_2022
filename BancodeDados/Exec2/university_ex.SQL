# Exercicio 2 -  26/09/2022
DROP DATABASE IF EXISTS universidade;
CREATE DATABASE universidade;
USE universidade;
DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
`CPF` VARCHAR(11) NOT NULL,
`Nome` VARCHAR(50) NOT NULL,
`Salario` DECIMAL(7,2) NOT NULL,
PRIMARY KEY (CPF)
);

DROP TABLE IF EXISTS `aluno`;
CREATE TABLE `aluno` (
`Matricula` VARCHAR(7) NOT NULL,
`Nome` VARCHAR(50) NOT NULL,
`DataNascimento` DATE NOT NULL,
`Curso` VARCHAR(5) NOT NULL,
PRIMARY KEY (Matricula)
);

DROP TABLE IF EXISTS `disciplina`;
CREATE TABLE `disciplina` (
`Codigo` VARCHAR(10) NOT NULL,
`Nome` VARCHAR(50) NOT NULL,
`Horas` INT(5) NOT NULL,
PRIMARY KEY (Codigo)
);

DROP TABLE IF EXISTS `prerequisito`;
CREATE TABLE `prerequisito` (
`CodigoDisciplina` VARCHAR(10) NOT NULL,
`CodigoPrerequisito` VARCHAR(10) NOT NULL,
CONSTRAINT Fk_CodigoDisciplinaPR FOREIGN KEY (CodigoDisciplina) REFERENCES disciplina(Codigo),
CONSTRAINT Fk_CodigoPrerequisitoPR FOREIGN KEY (CodigoPrerequisito) REFERENCES disciplina(Codigo)
);

CREATE TABLE `Oferta` (
`Codigo` INT(5) NOT NULL AUTO_INCREMENT,
`Semestre` INT(1) NOT NULL,
`Ano` INT(4) NOT NULL,
`Nota` DECIMAL(3,2) NOT NULL,
`Frequencia` VARCHAR(4) NOT NULL,
`CodigoDisciplina` VARCHAR(10) NOT NULL,
`Aluno` VARCHAR(7) NOT NULL,
`Professor` VARCHAR(11) NOT NULL,
PRIMARY KEY (Codigo),
CONSTRAINT Fk_CodigoDisciplina FOREIGN KEY (CodigoDisciplina) REFERENCES disciplina(Codigo),
CONSTRAINT Fk_Aluno FOREIGN KEY (Aluno) REFERENCES aluno(Matricula),
CONSTRAINT Fk_Professor FOREIGN KEY (Professor) REFERENCES professor(CPF)
);

INSERT INTO `Professor`
(`CPF`,`Nome`,`Salario`)
VALUES
('10120214450', 'Lucas Menezes',5420.00),
('11040540330', 'Bruno Santos',4200.00),
('10560930210', 'Marcos Lima',7150.00),
('10010220470', 'Elton Cardoso',6500.00),
('10190267390', 'Rafael Silva',2500.00),
('10432029020', 'Fernando Vaz',3880.00);

INSERT INTO `Aluno`
(`Matricula`,`Nome`,`DataNascimento`,`Curso`)
VALUES
(1918023, 'Camila Braga','1999-03-12', 'COM'),
(1918142, 'João Lage','1999-11-20', 'PRO'),
(1828201, 'Pedro Santos','1999-09-02', 'COM');

INSERT INTO `disciplina`
(`Codigo`,`Nome`,`Horas`)
VALUES
('CSI160','Cálculo Diferencial e Integral I',60),
('CSI030','Programação de Computadores I',60),
('CSI440','Banco de Dados I',60),
('CSI443','Matemática Discreta',60),
('CSI488','Algoritmos e Estruturas de Dados I',60);

INSERT INTO `prerequisito`
(`CodigoDisciplina`,`CodigoPrerequisito`)
VALUES
('CSI440','CSI443'),
('CSI440','CSI488'),
('CSI488','CSI030');


INSERT INTO `oferta`
(`CodigoDisciplina`,`Semestre`,`Ano`,`Nota`,`Frequencia`,`Aluno`,`Professor`)
VALUES
('CSI030',1,2019,8.6,'90%','1918023','10120214450'),
('CSI030',1,2019,7.4,'95%','1918142','10120214450'),
('CSI030',1,2019,2.5,'50%','1828201','10120214450'),
('CSI440',1,2019,4.7,'75%','1918142','11040540330'),
('CSI160',1,2019,9.5,'100%','1828201','10560930210'),
('CSI443',1,2019,7.7,'82%','1918023','10010220470'),
('CSI030',2,2019,6.0,'90%','1828201','10432029020'),
('CSI440',2,2019,6.7,'85%','1918142','10190267390'),
('CSI160',2,2019,8.2,'92%','1918023','10560930210'),
('CSI443',2,2019,9.0,'100%','1828201','10010220470');