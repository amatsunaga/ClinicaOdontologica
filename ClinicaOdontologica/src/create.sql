create table if not exists paciente (id int auto_increment primary key,
nome varchar(255),
sobrenome varchar(255),
enderecoId int not null,
rg varchar(20), dataCadastro date(15), CONSTRAINT FOREIGN KEY (enderecoId) REFERENCES Endereco(enderecoId));