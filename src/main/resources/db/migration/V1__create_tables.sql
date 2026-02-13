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

-- Raquetes
CREATE TABLE raquetes (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(255) NOT NULL,
  modelo VARCHAR(255) NOT NULL,
  preco NUMERIC(12,2) NOT NULL,
  vendida BOOLEAN NOT NULL DEFAULT FALSE
);

-- Vendas
CREATE TABLE vendas (
  id BIGSERIAL PRIMARY KEY,
  data_venda TIMESTAMP NOT NULL,
  valor_total NUMERIC(12,2) NOT NULL,
  cliente_id BIGINT NOT NULL REFERENCES clientes(id),
  vendedor_id BIGINT NOT NULL REFERENCES vendedores(id)
);

-- Relação venda x raquetes
CREATE TABLE venda_raquetes (
  venda_id BIGINT NOT NULL REFERENCES vendas(id) ON DELETE CASCADE,
  raquete_id BIGINT NOT NULL REFERENCES raquetes(id),
  PRIMARY KEY (venda_id, raquete_id)
);

CREATE INDEX idx_vendas_data ON vendas(data_venda);
