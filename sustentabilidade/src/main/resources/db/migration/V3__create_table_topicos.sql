CREATE TABLE tb_topicos (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    conteudo TEXT NOT NULL,
    likes INTEGER DEFAULT 0,
    deslikes INTEGER DEFAULT 0,
    usuario_id BIGINT NOT NULL,
    criado_em TIMESTAMP NOT NULL,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES tb_usuarios(id)
);
