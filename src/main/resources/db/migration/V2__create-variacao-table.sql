CREATE TABLE Variacao (
    id SERIAL PRIMARY KEY,
    id_produto INT REFERENCES Produto(id),
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255)
);