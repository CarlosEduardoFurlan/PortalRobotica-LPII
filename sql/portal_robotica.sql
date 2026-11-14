-- Portal de Robótica IFMS - estrutura final N2 (instalação nova)
CREATE TABLE estudante (
 id BIGSERIAL PRIMARY KEY, nome VARCHAR(150) NOT NULL, minibio TEXT, foto VARCHAR(255),
 email VARCHAR(254), senha_hash VARCHAR(255), ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE UNIQUE INDEX uk_estudante_email_normalizado ON estudante (lower(trim(email))) WHERE email IS NOT NULL;

CREATE TABLE periodo_letivo (
 id BIGSERIAL PRIMARY KEY, ano SMALLINT NOT NULL, semestre SMALLINT NOT NULL CHECK(semestre IN(1,2)), UNIQUE(ano,semestre)
);

CREATE TABLE coordenador (
 id BIGSERIAL PRIMARY KEY, nome VARCHAR(150) NOT NULL, minibio TEXT, foto VARCHAR(255),
 email VARCHAR(254), senha_hash VARCHAR(255), ativo BOOLEAN NOT NULL DEFAULT TRUE
);
CREATE UNIQUE INDEX uk_coordenador_email_normalizado ON coordenador (lower(trim(email))) WHERE email IS NOT NULL;

CREATE TABLE atividade (
 id BIGSERIAL PRIMARY KEY, titulo VARCHAR(180) NOT NULL,
 tipo VARCHAR(40) NOT NULL CHECK(tipo IN('projeto','estagio','tarefa','oficina','palestra','evento','competicao','visita')),
 descricao TEXT NOT NULL, data_inicio DATE NOT NULL, data_fim DATE,
 situacao VARCHAR(30) NOT NULL CHECK(situacao IN('planejada','em_andamento','concluida')),
 coordenador_id BIGINT NOT NULL REFERENCES coordenador(id) ON DELETE RESTRICT,
 CHECK(data_fim IS NULL OR data_fim>=data_inicio)
);
CREATE TABLE atividade_periodo (
 atividade_id BIGINT NOT NULL REFERENCES atividade(id) ON DELETE CASCADE,
 periodo_id BIGINT NOT NULL REFERENCES periodo_letivo(id) ON DELETE RESTRICT,
 PRIMARY KEY(atividade_id,periodo_id)
);
CREATE TABLE participacao (
 estudante_id BIGINT NOT NULL REFERENCES estudante(id) ON DELETE RESTRICT,
 atividade_id BIGINT NOT NULL REFERENCES atividade(id) ON DELETE CASCADE,
 funcao VARCHAR(100), descricao_contribuicao TEXT, PRIMARY KEY(estudante_id,atividade_id)
);
CREATE TABLE conquista (
 id BIGSERIAL PRIMARY KEY, atividade_id BIGINT NOT NULL REFERENCES atividade(id) ON DELETE CASCADE,
 titulo VARCHAR(180) NOT NULL, data DATE, descricao TEXT
);
CREATE TABLE token_redefinicao_senha (
 id BIGSERIAL PRIMARY KEY,
 estudante_id BIGINT REFERENCES estudante(id) ON DELETE CASCADE,
 coordenador_id BIGINT REFERENCES coordenador(id) ON DELETE CASCADE,
 token_hash CHAR(64) NOT NULL UNIQUE, criado_em TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
 expira_em TIMESTAMP WITH TIME ZONE NOT NULL, usado_em TIMESTAMP WITH TIME ZONE,
 CONSTRAINT ck_token_um_usuario CHECK((estudante_id IS NOT NULL AND coordenador_id IS NULL) OR (estudante_id IS NULL AND coordenador_id IS NOT NULL))
);
CREATE INDEX idx_token_redefinicao_expira ON token_redefinicao_senha(expira_em);

-- O primeiro coordenador deve ser criado com e-mail e hash bcrypt conforme README; não há senhas reais neste script.
