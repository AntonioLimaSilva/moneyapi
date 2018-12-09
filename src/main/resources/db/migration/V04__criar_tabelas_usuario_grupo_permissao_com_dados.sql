CREATE TABLE usuario (
	id INT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR (100) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE grupo (
    id INT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	id INT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_grupo (
    id_usuario INT(20) NOT NULL,
    id_grupo INT(20) NOT NULL,
    PRIMARY KEY (id_usuario, id_grupo),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_grupo) REFERENCES grupo(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE grupo_permissao (
    id_grupo INT(20) NOT NULL,
    id_permissao INT(20) NOT NULL,
    PRIMARY KEY (id_grupo, id_permissao),
    FOREIGN KEY (id_grupo) REFERENCES grupo(id),
    FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nome, email, senha) values ('Administrador', 'admin@algamoney.com', '$2a$10$57yVtVxsP/4F.tFTjUx18OKD.rpULb8Dx9hRf/ZjyeF5CFAbh7Kb2');
INSERT INTO usuario (nome, email, senha) values ('Maria Silva', 'maria@algamoney.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO grupo (nome) VALUES ('Administrador');
INSERT INTO grupo (nome) VALUES ('Atendente');

INSERT INTO permissao (nome) values ('ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (nome) values ('ROLE_PESQUISAR_CATEGORIA');
INSERT INTO permissao (nome) values ('ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (nome) values ('ROLE_REMOVER_PESSOA');
INSERT INTO permissao (nome) values ('ROLE_PESQUISAR_PESSOA');
INSERT INTO permissao (nome) values ('ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (nome) values ('ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao (nome) values ('ROLE_PESQUISAR_LANCAMENTO');

INSERT INTO usuario_grupo (id_usuario, id_grupo) VALUES (1, 1);
INSERT INTO usuario_grupo (id_usuario, id_grupo) VALUES (2, 2);

INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 1);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 2);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 3);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 4);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 5);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 6);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 7);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (1, 8);

INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 2);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 5);
INSERT INTO grupo_permissao (id_grupo, id_permissao) VALUES (2, 8);