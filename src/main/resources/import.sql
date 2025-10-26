INSERT INTO cozinha (id, nome) VALUES (1, 'Italiana');
INSERT INTO cozinha (id, nome) VALUES (2, 'Chinesa');
INSERT INTO cozinha (id, nome) VALUES (3, 'Mexicana');

INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id) VALUES (1, 'Restaurante A', 5.00, 1);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id) VALUES (2, 'Restaurante B', 7.50, 2);
INSERT INTO restaurante (id, nome, taxa_frete, cozinha_id) VALUES (3, 'Restaurante C', 6.00, 3);

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

INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 2);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 1);
INSERT INTO restaurante_formas_pagamento (restaurante_id, forma_pagamento_id) VALUES (3, 3)