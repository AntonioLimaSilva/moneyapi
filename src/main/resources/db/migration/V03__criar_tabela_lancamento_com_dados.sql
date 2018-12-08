CREATE TABLE lancamento (
	id INT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR (100) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE NULL,
	valor NUMERIC (10, 2) NULL,
	observacao VARCHAR (100),
	tipo VARCHAR (20) NOT NULL,
	id_pessoa INT NOT NULL,
	id_categoria INT NOT NULL,	
	CONSTRAINT fk_pessoa_lacamento FOREIGN KEY (id_pessoa) REFERENCES pessoa (id),
	CONSTRAINT fk_categoria_lacamento FOREIGN KEY (id_categoria) REFERENCES categoria (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_pessoa, id_categoria) 
VALUES ( 'Biscoito', '2018-04-21', '2018-02-20', 10.90, 'Cliente do alto do cristo', 'RECEITA', 1, 1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, id_pessoa, id_categoria) 
VALUES ( 'Caneta Big', '2018-04-29', '2018-03-30', 3.90, 'Cliente Novo', 'RECEITA', 2, 5);