CREATE TABLE pessoa (
	id INT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR (50) NOT NULL,
	ativo BIT NOT NULL,
	logradouro VARCHAR (100) NULL,
	numero VARCHAR(10) NULL,
	complemento VARCHAR(200) NULL,
	bairro VARCHAR(100) NULL,
	cep VARCHAR (20) NULL,
	estado VARCHAR(50) NULL,
	cidade VARCHAR(50) NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, estado, cidade) VALUES
('Antonio Lima da Silva', 1, 'Av. Dom Emilio de Morais', '201', 'Perto do posto de gasolina', 'Dom Expedido',
'190-0000', 'Ceará', 'Sobral');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, estado, cidade) VALUES
('Paulo da Silva', 1, 'Av. Alto Novo', '301', 'Perto do posto de saúde', 'Terrenos Novos',
'190-0000', 'Ceará', 'Sobral');