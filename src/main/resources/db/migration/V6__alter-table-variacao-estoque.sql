ALTER TABLE Variacao
ALTER COLUMN id_produto SET NOT NULL;

ALTER TABLE Variacao
DROP CONSTRAINT IF EXISTS variacao_id_produto_fkey;

 -- 1. Remover id_produto
 ALTER TABLE Estoque
 DROP COLUMN id_produto;

 -- 2. Adicionar id_variacao (relacionamento correto)
 ALTER TABLE Estoque
 ADD COLUMN id_variacao INT NOT NULL REFERENCES Variacao(id) ON DELETE CASCADE;

 -- 3. Adicionar constraint UNIQUE para id_variacao (1 estoque por variação)
 ALTER TABLE Estoque
 ADD CONSTRAINT uq_estoque_variacao UNIQUE(id_variacao);

 -- 4. Adicionar campos de controle de estoque
 ALTER TABLE Estoque
 ADD COLUMN estoque_minimo INT DEFAULT 0,
 ADD COLUMN estoque_maximo INT,
 ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

 -- 5. Criar índice para performance
 CREATE INDEX idx_estoque_variacao ON Estoque(id_variacao);