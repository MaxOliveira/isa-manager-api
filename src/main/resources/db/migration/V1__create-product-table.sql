-- CATEGORIA
CREATE TABLE Categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255)
);

-- PRODUTO
CREATE TABLE Produto (
    id SERIAL PRIMARY KEY,
    codigo_sku VARCHAR(50) UNIQUE NOT NULL,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(255),
    id_categoria INT REFERENCES Categoria(id),
    cor VARCHAR(50),
    tamanho VARCHAR(10),
    preco_custo DECIMAL(10,2),
    preco_venda DECIMAL(10,2),
    ativo BOOLEAN DEFAULT TRUE
);

-- FORNECEDOR
CREATE TABLE Fornecedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cnpj VARCHAR(18),
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(255)
);

-- ESTOQUE (quantidade atual)
CREATE TABLE Estoque (
    id SERIAL PRIMARY KEY,
    id_produto INT REFERENCES Produto(id),
    quantidade INT NOT NULL DEFAULT 0,
    localizacao VARCHAR(100)
);

-- MOVIMENTAÇÃO DE ESTOQUE (entradas e saídas)
CREATE TABLE MovimentacaoEstoque (
    id SERIAL PRIMARY KEY,
    id_produto INT REFERENCES Produto(id),
    tipo CHAR(1) CHECK (tipo IN ('E', 'S')), -- E = entrada, S = saída
    quantidade INT NOT NULL,
    data_mov TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    origem VARCHAR(100), -- Ex: 'Compra', 'Venda', 'Ajuste Manual'
    id_referencia INT -- ID da venda ou compra associada
);

-- COMPRA (entrada de estoque)
CREATE TABLE Compra (
    id SERIAL PRIMARY KEY,
    id_fornecedor INT REFERENCES Fornecedor(id),
    data_compra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_total DECIMAL(10,2),
    status VARCHAR(20) DEFAULT 'ABERTA' -- ABERTA, RECEBIDA, CANCELADA
);

-- ITENS DA COMPRA
CREATE TABLE ItemCompra (
    id SERIAL PRIMARY KEY,
    id_compra INT REFERENCES Compra(id),
    id_produto INT REFERENCES Produto(id),
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2)
);

-- CLIENTE
CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    cpf VARCHAR(14),
    telefone VARCHAR(20),
    email VARCHAR(100)
);

-- VENDA
CREATE TABLE Venda (
    id SERIAL PRIMARY KEY,
    id_cliente INT REFERENCES Cliente(id),
    data_venda TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    valor_total DECIMAL(10,2),
    forma_pagamento VARCHAR(50),
    status VARCHAR(20) DEFAULT 'FINALIZADA'
);

-- ITENS DA VENDA
CREATE TABLE ItemVenda (
    id SERIAL PRIMARY KEY,
    id_venda INT REFERENCES Venda(id),
    id_produto INT REFERENCES Produto(id),
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2)
);

-- USUÁRIOS DO SISTEMA
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    senha_hash VARCHAR(255),
    perfil VARCHAR(50) -- ADMIN, VENDEDOR, GERENTE
);
