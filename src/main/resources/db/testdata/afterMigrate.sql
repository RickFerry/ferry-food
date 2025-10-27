-- sql
set foreign_key_checks = 0;

delete from usuarios_grupos;
delete from grupos_permissoes;
delete from usuario;
delete from grupo;
delete from permissao;
delete from restaurante_formas_pagamento;
delete from forma_pagamento;
delete from produto;
delete from restaurante;
delete from cozinha;
delete from cidade;
delete from estado;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

INSERT INTO cozinha (id, nome) VALUES (1, 'Italiana');
INSERT INTO cozinha (id, nome) VALUES (2, 'Chinesa');
INSERT INTO cozinha (id, nome) VALUES (3, 'Mexicana');

INSERT INTO forma_pagamento (id, descricao) VALUES (1, 'Dinheiro');
INSERT INTO forma_pagamento (id, descricao) VALUES (2, 'Cartão de Crédito');
INSERT INTO forma_pagamento (id, descricao) VALUES (3, 'Cartão de Débito');

INSERT INTO permissao (id, nome) VALUES (1, 'ROLE_ADMIN');
INSERT INTO permissao (id, nome) VALUES (2, 'ROLE_USER');
INSERT INTO permissao (id, nome) VALUES (3, 'ROLE_MANAGER');

INSERT INTO estado (id, nome) VALUES (1, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (2, 'Rio de Janeiro');
INSERT INTO estado (id, nome) VALUES (3, 'Minas Gerais');

INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'São Paulo', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (2, 'Rio de Janeiro', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (3, 'Belo Horizonte', 3);

-- additional permissions
INSERT INTO permissao (id, descricao, nome) VALUES (4, 'Permite gerenciar restaurantes', 'ROLE_RESTAURANTE_MANAGER');
INSERT INTO permissao (id, descricao, nome) VALUES (5, 'Permite gerenciar usuários', 'ROLE_USER_MANAGER');
INSERT INTO permissao (id, descricao, nome) VALUES (6, 'Permite gerenciar pedidos', 'ROLE_ORDER_MANAGER');

INSERT INTO grupo (id, nome) VALUES (1, 'Administradores');
INSERT INTO grupo (id, nome) VALUES (2, 'Usuários');
INSERT INTO grupo (id, nome) VALUES (3, 'Gerentes de Restaurante');

INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (1, 1);
INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (1, 2);
INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (1, 3);
INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (1, 4);
INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (1, 5);
INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (1, 6);
INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (2, 2);
INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (3, 4);
INSERT INTO grupos_permissoes (grupo_id, permissao_id) VALUES (3, 6);

-- users (passwords are already hashed)
-- senha: admin123
INSERT INTO usuario (id, data_cadastro, email, nome, senha)
VALUES (1, NOW(), 'ferry@gmail.com', 'Ferry Admin', '$2a$10$7QJH6j1Z0G8Q9Z0Z0Z0ZeuK1Z0Z0ZeuK1Z0Z0ZeuK1Z0Z0ZeuK1Z0Z0Ze');

-- senha: user123
INSERT INTO usuario (id, data_cadastro, email, nome, senha)
VALUES (2, NOW(), 'rick@gmail.com', 'Rick User', '$2a$10$8RKH7k2Y1H9R0Y1Y1Y1ZeuK2Y1H9R0Y1Y1Y1ZeuK2Y1H9R0Y1Y1Y1ZeuK2Y1H9R0Y1Y1Y1ZeuK2');

-- senha: manager123
INSERT INTO usuario (id, data_cadastro, email, nome, senha)
VALUES (3, NOW(), 'kalel@gmail.com', 'Kalel Manager', '$2a$10$9SLI8l3Z2I0S1Z2Z2Z2ZeuK3Z2I0S1Z2Z2Z2ZeuK3Z2I0S1Z2Z2Z2ZeuK3Z2I0S1Z2Z2Z2ZeuK3');

INSERT INTO usuarios_grupos (usuario_id, grupo_id) VALUES (1, 1);
INSERT INTO usuarios_grupos (usuario_id, grupo_id) VALUES (2, 2);
INSERT INTO usuarios_grupos (usuario_id, grupo_id) VALUES (3, 3);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, logradouro, numero, complemento, bairro, cep, data_cadastro, data_atualizacao, cidade_id)
VALUES (1, 'Restaurante A', 5.00, 1, 'Rua A', '100', 'Sala 1', 'Bairro A', '01000-000', NOW(), NOW(), 1);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, logradouro, numero, complemento, bairro, cep, data_cadastro, data_atualizacao, cidade_id)
VALUES (2, 'Restaurante B', 7.50, 2, 'Rua B', '200', '', 'Bairro B', '02000-000', NOW(), NOW(), 2);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, logradouro, numero, complemento, bairro, cep, data_cadastro, data_atualizacao, cidade_id)
VALUES (3, 'Restaurante C', 6.00, 3, 'Rua C', '300', 'Loja 2', 'Bairro C', '03000-000', NOW(), NOW(), 3);

INSERT INTO produto (id, nome, descricao, ativo, preco, restaurante_id)
VALUES (1, 'Pizza Margherita', 'Pizza tradicional italiana com molho de tomate, mussarela e manjericão.', true, 30.00, 1);

INSERT INTO produto (id, nome, descricao, ativo, preco, restaurante_id)
VALUES (2, 'Chow Mein', 'Prato chinês de macarrão frito com legumes e carne.', true, 25.00, 2);

INSERT INTO produto (id, nome, descricao, ativo, preco, restaurante_id)
VALUES (3, 'Tacos', 'Tradicional prato mexicano com tortillas recheadas.', true, 20.00, 3);

INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 2);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 1);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 3);
