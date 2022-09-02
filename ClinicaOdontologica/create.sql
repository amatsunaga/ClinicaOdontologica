CREATE TABLE IF NOT EXISTS Endereco (
enderecoId INT AUTO_INCREMENT PRIMARY KEY,
NUMERO INT,
CIDADE VARCHAR(50),
BAIRRO VARCHAR(50),
CEP VARCHAR(8)
);

create table if not exists paciente (
id int auto_increment primary key,
nome varchar(255),
sobrenome varchar(255),
enderecoId int not null,
rg varchar(20),
dataCadastro date
--FOREIGN KEY (enderecoId) REFERENCES Endereco(enderecoId)
);

CREATE TABLE IF NOT EXISTS Consulta (
consultaId INT AUTO_INCREMENT PRIMARY KEY,
dentistaId INT,
pacienteId INT,
dataConsulta DATE
);