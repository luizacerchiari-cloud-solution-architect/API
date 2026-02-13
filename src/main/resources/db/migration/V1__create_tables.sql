-- Clientes
CREATE TABLE clientes (
                          id BIGSERIAL PRIMARY KEY,
                          nome VARCHAR(255) NOT NULL,
                          elegivel_desconto BOOLEAN NOT NULL DEFAULT FALSE
);

-- Vendedores
CREATE TABLE vendedores (
                            id BIGSERIAL PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL,
                            cpf VARCHAR(20) NOT NULL UNIQUE,
                            matricula VARCHAR(20) NOT NULL UNIQUE
);

-- Camisas
CREATE TABLE camisas (
                         id BIGSERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         modelo VARCHAR(255) NOT NULL,
                         preco DOUBLE PRECISION NOT NULL,
                         vendida BOOLEAN NOT NULL DEFAULT FALSE
);

-- Vendas
CREATE TABLE vendas (
                        id BIGSERIAL PRIMARY KEY,
                        data_venda TIMESTAMP NOT NULL,
                        valor_total DOUBLE PRECISION NOT NULL,
                        cliente_id BIGINT NOT NULL REFERENCES clientes(id),
                        vendedor_id BIGINT NOT NULL REFERENCES vendedores(id)
);

-- Relação venda x camisas
CREATE TABLE venda_camisas (
                               venda_id BIGINT NOT NULL REFERENCES vendas(id) ON DELETE CASCADE,
                               camisa_id BIGINT NOT NULL REFERENCES camisas(id),
                               PRIMARY KEY (venda_id, camisa_id)
);

CREATE INDEX idx_vendas_data ON vendas(data_venda);