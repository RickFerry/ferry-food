-- sql
SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM usuarios_grupos;
DELETE FROM grupos_permissoes;
DELETE FROM usuario;
DELETE FROM grupo;
DELETE FROM permissao;
DELETE FROM restaurante_formas_pagamento;
DELETE FROM forma_pagamento;
DELETE FROM produto;
DELETE FROM itens_pedido;
DELETE FROM pedido;
DELETE FROM restaurante;
DELETE FROM cozinha;
DELETE FROM cidade;
DELETE FROM estado;

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE cidade AUTO_INCREMENT = 1;
ALTER TABLE cozinha AUTO_INCREMENT = 1;
ALTER TABLE estado AUTO_INCREMENT = 1;
ALTER TABLE forma_pagamento AUTO_INCREMENT = 1;
ALTER TABLE grupo AUTO_INCREMENT = 1;
ALTER TABLE permissao AUTO_INCREMENT = 1;
ALTER TABLE produto AUTO_INCREMENT = 1;
ALTER TABLE restaurante AUTO_INCREMENT = 1;
ALTER TABLE usuario AUTO_INCREMENT = 1;
ALTER TABLE pedido AUTO_INCREMENT = 1;
ALTER TABLE itens_pedido AUTO_INCREMENT = 1;

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

/* additional permissions */
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

/* users (passwords are already hashed) */
INSERT INTO usuario (id, data_cadastro, email, nome, senha) VALUES (1, NOW(), 'ferry@gmail.com', 'Ferry Admin', '$2a$10$7QJH6j1Z0G8Q9Z0Z0Z0ZeuK1Z0Z0ZeuK1Z0Z0ZeuK1Z0Z0ZeuK1Z0Z0Ze');
INSERT INTO usuario (id, data_cadastro, email, nome, senha) VALUES (2, NOW(), 'rick@gmail.com', 'Rick User', '$2a$10$8RKH7k2Y1H9R0Y1Y1Y1ZeuK2Y1H9R0Y1Y1Y1ZeuK2Y1H9R0Y1Y1Y1ZeuK2Y1H9R0Y1Y1Y1ZeuK2');
INSERT INTO usuario (id, data_cadastro, email, nome, senha) VALUES (3, NOW(), 'kalel@gmail.com', 'Kalel Manager', '$2a$10$9SLI8l3Z2I0S1Z2Z2Z2ZeuK3Z2I0S1Z2Z2Z2ZeuK3Z2I0S1Z2Z2Z2ZeuK3Z2I0S1Z2Z2Z2ZeuK3');

INSERT INTO usuarios_grupos (usuario_id, grupo_id) VALUES (1, 1);
INSERT INTO usuarios_grupos (usuario_id, grupo_id) VALUES (2, 2);
INSERT INTO usuarios_grupos (usuario_id, grupo_id) VALUES (3, 3);

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, logradouro, numero, complemento, bairro, cep, data_cadastro, data_atualizacao, cidade_id) VALUES (1, 'Restaurante A', 5.00, 1, 'Rua A', '100', 'Sala 1', 'Bairro A', '01000-000', NOW(), NOW(), 1);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, logradouro, numero, complemento, bairro, cep, data_cadastro, data_atualizacao, cidade_id) VALUES (2, 'Restaurante B', 7.50, 2, 'Rua B', '200', '', 'Bairro B', '02000-000', NOW(), NOW(), 2);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id, logradouro, numero, complemento, bairro, cep, data_cadastro, data_atualizacao, cidade_id) VALUES (3, 'Restaurante C', 6.00, 3, 'Rua C', '300', 'Loja 2', 'Bairro C', '03000-000', NOW(), NOW(), 3);

INSERT INTO produto (id, nome, descricao, ativo, preco, restaurante_id) VALUES (1, 'Pizza Margherita', 'Pizza tradicional italiana com molho de tomate, mussarela e manjericão.', 1, 30.00, 1);
INSERT INTO produto (id, nome, descricao, ativo, preco, restaurante_id) VALUES (2, 'Chow Mein', 'Prato chinês de macarrão frito com legumes e carne.', 1, 25.00, 2);
INSERT INTO produto (id, nome, descricao, ativo, preco, restaurante_id) VALUES (3, 'Tacos', 'Tradicional prato mexicano com tortillas recheadas.', 1, 20.00, 3);

INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 2);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 1);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 3);

INSERT INTO pedido (id, subtotal, taxa_frete, valor_total, status, data_criacao, data_confirmacao, data_cancelamento, data_entrega, forma_pagamento_id, restaurante_id, cliente_id, logradouro, numero, complemento, bairro, cep, cidade_id) VALUES (1, 50.00, 5.00, 55.00, 'CONFIRMADO', NOW(), NOW(), NULL, NULL, 1, 1, 2, 'Av. Cliente', '123', 'Apto 45', 'Bairro Cliente', '04000-000', 1);
INSERT INTO pedido (id, subtotal, taxa_frete, valor_total, status, data_criacao, data_confirmacao, data_cancelamento, data_entrega, forma_pagamento_id, restaurante_id, cliente_id, logradouro, numero, complemento, bairro, cep, cidade_id) VALUES (2, 30.00, 7.50, 37.50, 'ENTREGUE', NOW(), NOW(), NULL, NOW(), 2, 2, 3, 'Rua Cliente', '456', '', 'Bairro Cliente 2', '05000-000', 2);
INSERT INTO pedido (id, subtotal, taxa_frete, valor_total, status, data_criacao, data_confirmacao, data_cancelamento, data_entrega, forma_pagamento_id, restaurante_id, cliente_id, logradouro, numero, complemento, bairro, cep, cidade_id) VALUES (3, 40.00, 6.00, 46.00, 'CANCELADO', NOW(), NULL, NOW(), NULL, 1, 3, 1, 'Praça Cliente', '789', 'Casa', 'Bairro Cliente 3', '06000-000', 3);

INSERT INTO itens_pedido (id, preco_unitario, preco_total, quantidade, observacao, pedido_id, produto_id) VALUES (1, 25.00, 50.00, 2, 'Sem cebola', 1, 1);
INSERT INTO itens_pedido (id, preco_unitario, preco_total, quantidade, observacao, pedido_id, produto_id) VALUES (2, 30.00, 30.00, 1, 'Com extra molho', 2, 2);
INSERT INTO itens_pedido (id, preco_unitario, preco_total, quantidade, observacao, pedido_id, produto_id) VALUES (3, 20.00, 40.00, 2, 'Apenas carne', 3, 3);
