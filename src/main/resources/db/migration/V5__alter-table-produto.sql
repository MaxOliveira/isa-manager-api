ALTER TABLE Produto
ALTER COLUMN id_categoria SET NOT NULL;

ALTER TABLE Produto
ADD CONSTRAINT fk_produto_categoria
FOREIGN KEY (id_categoria)
REFERENCES Categoria(id);