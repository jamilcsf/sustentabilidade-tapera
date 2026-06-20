CREATE TABLE tb_denuncias (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    descricao TEXT,
    localizacao VARCHAR(255),
    referencia VARCHAR(255),
    status VARCHAR(50) DEFAULT 'aberta',
    lat DOUBLE PRECISION,
    lng DOUBLE PRECISION,
    criado_em TIMESTAMP NOT NULL
);