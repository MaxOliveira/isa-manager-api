CREATE TABLE variacao_atributo (
    id     SERIAL PRIMARY KEY,
    id_variacao       INT NOT NULL,
    nome_atributo     VARCHAR(50) NOT NULL,
    valor_atributo    VARCHAR(50) NOT NULL,

    FOREIGN KEY (id_variacao) REFERENCES variacao(id)
);

ALTER TABLE Produto ADD COLUMN data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP;