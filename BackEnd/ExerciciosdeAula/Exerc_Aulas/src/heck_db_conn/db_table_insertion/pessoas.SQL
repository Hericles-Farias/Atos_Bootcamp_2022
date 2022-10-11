use reuniao; 
drop table if exists Pessoa2;
CREATE TABLE Pessoa2;
(ID int NOT NUll PRIMARY KEY, nome VARCHAR(40), email VARCHAR(40), cargo VARCHAR(30)); 

INSERT INTO pessoa2 values (3,'João Silva', ' joao@gmail.com', 'Aluno');

INSERT INTO pessoa2 values (4,'Paulo Vargas', 'pauloVargas@ufn.edu.br', 'Professor');

SELECT * FROM pessoa2; 