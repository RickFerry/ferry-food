CREATE TABLE cidade
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(255)          NULL,
    estado_id BIGINT                NULL,
    CONSTRAINT pk_cidade PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE cozinha
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(255)          NULL,
    CONSTRAINT pk_cozinha PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE estado
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(255)          NULL,
    CONSTRAINT pk_estado PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE forma_pagamento
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    descricao VARCHAR(255)          NULL,
    CONSTRAINT pk_formapagamento PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE grupo
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(255)          NULL,
    CONSTRAINT pk_grupo PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE grupos_permissoes
(
    grupo_id     BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL,
    CONSTRAINT pk_grupos_permissoes PRIMARY KEY (grupo_id, permissao_id)
) engine=InnoDB;

CREATE TABLE permissao
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    nome      VARCHAR(255)          NULL,
    descricao VARCHAR(255)          NULL,
    CONSTRAINT pk_permissao PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE produto
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    nome           VARCHAR(255)          NULL,
    descricao      VARCHAR(255)          NULL,
    ativo          BIT(1)                NULL,
    preco          DECIMAL               NULL,
    restaurante_id BIGINT                NULL,
    CONSTRAINT pk_produto PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE restaurante
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    nome             VARCHAR(255)          NULL,
    taxa_frete       DECIMAL               NULL,
    data_cadastro    datetime              NOT NULL,
    data_atualizacao datetime              NOT NULL,
    cozinha_id       BIGINT                NULL,
    logradouro       VARCHAR(255)          NULL,
    numero           VARCHAR(255)          NULL,
    complemento      VARCHAR(255)          NULL,
    bairro           VARCHAR(255)          NULL,
    cep              VARCHAR(255)          NULL,
    cidade_id        BIGINT                NULL,
    CONSTRAINT pk_restaurante PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE restaurante_formas_pagamento
(
    forma_pagamento_id BIGINT NOT NULL,
    restaurante_id     BIGINT NOT NULL,
    CONSTRAINT pk_restaurante_formaspagamento PRIMARY KEY (forma_pagamento_id, restaurante_id)
) engine=InnoDB;

CREATE TABLE usuario
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    nome          VARCHAR(255)          NULL,
    email         VARCHAR(255)          NULL,
    senha         VARCHAR(255)          NULL,
    data_cadastro datetime              NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (id)
) engine=InnoDB;

CREATE TABLE usuarios_grupos
(
    grupo_id   BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    CONSTRAINT pk_usuarios_grupos PRIMARY KEY (grupo_id, usuario_id)
) engine=InnoDB;

ALTER TABLE cidade
    ADD CONSTRAINT FK_CIDADE_ON_ESTADO FOREIGN KEY (estado_id) REFERENCES estado (id);

ALTER TABLE grupos_permissoes
    ADD CONSTRAINT fk_grupos_permissoes_on_permissao FOREIGN KEY (permissao_id) REFERENCES permissao (id);

ALTER TABLE grupos_permissoes
    ADD CONSTRAINT fk_grupos_permissoes_on_grupo FOREIGN KEY (grupo_id) REFERENCES grupo (id);

ALTER TABLE produto
    ADD CONSTRAINT FK_PRODUTO_ON_RESTAURANTE FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ON_COZINHA FOREIGN KEY (cozinha_id) REFERENCES cozinha (id);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ON_CIDADE FOREIGN KEY (cidade_id) REFERENCES cidade (id);

ALTER TABLE restaurante_formas_pagamento
    ADD CONSTRAINT fk_resforpag_on_forma_pagamento FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (id);

ALTER TABLE restaurante_formas_pagamento
    ADD CONSTRAINT fk_resforpag_on_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE usuarios_grupos
    ADD CONSTRAINT fk_usugru_on_grupo FOREIGN KEY (grupo_id) REFERENCES grupo (id);

ALTER TABLE usuarios_grupos
    ADD CONSTRAINT fk_usugru_on_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE usuario
    ADD CONSTRAINT uc_usuario_email UNIQUE (email);

CREATE TABLE pedido
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    subtotal           DECIMAL               NULL,
    taxa_frete         DECIMAL               NULL,
    valor_total        DECIMAL               NULL,
    status             VARCHAR(255)          NULL,
    data_criacao       datetime              NULL,
    data_confirmacao   datetime              NULL,
    data_cancelamento  datetime              NULL,
    data_entrega       datetime              NULL,
    forma_pagamento_id BIGINT                NOT NULL,
    restaurante_id     BIGINT                NOT NULL,
    cliente_id         BIGINT                NOT NULL,
    logradouro         VARCHAR(255)          NULL,
    numero             VARCHAR(255)          NULL,
    complemento        VARCHAR(255)          NULL,
    bairro             VARCHAR(255)          NULL,
    cep                VARCHAR(255)          NULL,
    cidade_id          BIGINT                NULL,
    CONSTRAINT pk_pedido PRIMARY KEY (id)
);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_CIDADE FOREIGN KEY (cidade_id) REFERENCES cidade (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES usuario (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_FORMA_PAGAMENTO FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento (id);

ALTER TABLE pedido
    ADD CONSTRAINT FK_PEDIDO_ON_RESTAURANTE FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

CREATE TABLE itens_pedido
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    preco_unitario DECIMAL               NULL,
    preco_total    DECIMAL               NULL,
    quantidade     INT                   NULL,
    observacao     VARCHAR(255)          NULL,
    pedido_id      BIGINT                NOT NULL,
    produto_id     BIGINT                NOT NULL,
    CONSTRAINT pk_itens_pedido PRIMARY KEY (id)
);

ALTER TABLE itens_pedido
    ADD CONSTRAINT FK_ITENS_PEDIDO_ON_PEDIDO FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE itens_pedido
    ADD CONSTRAINT FK_ITENS_PEDIDO_ON_PRODUTO FOREIGN KEY (produto_id) REFERENCES produto (id);