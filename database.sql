create database couserjdbc;
use couserjdbc;

CREATE TABLE departamento (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Nome varchar(60) DEFAULT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE vendedor (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Nome varchar(60) NOT NULL,
  Email varchar(100) NOT NULL,
  DataNascimento datetime NOT NULL,
  BaseSalario double NOT NULL,
  DepartamentoId int(11) NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (DepartamentoId) REFERENCES departamento (id)
);

INSERT INTO departamento (Nome) VALUES 
  ('Computadores'),
  ('Electronicos'),
  ('Fashion'),
  ('Livros');

INSERT INTO Vendedor (Nome, Email, DataNascimento, BaseSalario, DepartamentoId) VALUES 
  ('Bob Silva','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Ribeiro','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Salomao','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Batista','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Santana','alex@gmail.com','1997-03-04 00:00:00',3000,2);
  
   -- busca por id:
SELECT vendedor.*,departamento.Nome as DepNome
FROM vendedor INNER JOIN departamento
ON vendedor.DepartamentoId = departamento.Id
WHERE vendedor.Id = 1;

 -- busca por id do departamento
SELECT vendedor.*,departamento.Nome as DepNome
FROM vendedor INNER JOIN departamento
ON vendedor.DepartamentoId = departamento.Id
WHERE DepartamentoId = 2
ORDER BY Nome;

-- busca todos os departamentos
SELECT vendedor.*,departamento.Nome as DepNome
FROM vendedor INNER JOIN departamento
ON vendedor.DepartamentoId = departamento.Id
ORDER BY Nome;

-- Inserindo Venderes
INSERT INTO vendedor
(Nome, Email, DataNascimento, BaseSalario, DepartamentoId)
VALUES
('Carl Teixeira','carl@gmail.com','1988-01-15 00:00:00',2.500,4);

-- atualização dos dados do vendedor pelo Id
UPDATE vendedor
SET Nome = 'Donaldi Santana', Email = 'donald@gmail.com', DataNascimento = '2000-01-09 00:00:00', BaseSalario = 5000.0, DepartamentoId = 3
WHERE Id = 5;