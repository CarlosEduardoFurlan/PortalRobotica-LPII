-- Migração incremental N1 -> N2. Preserva IDs, dados e relacionamentos.
BEGIN;
ALTER TABLE estudante ADD COLUMN IF NOT EXISTS email VARCHAR(254);
ALTER TABLE estudante ADD COLUMN IF NOT EXISTS senha_hash VARCHAR(255);
ALTER TABLE estudante ADD COLUMN IF NOT EXISTS ativo BOOLEAN NOT NULL DEFAULT TRUE;
ALTER TABLE coordenador ADD COLUMN IF NOT EXISTS minibio TEXT;
ALTER TABLE coordenador ADD COLUMN IF NOT EXISTS foto VARCHAR(255);
ALTER TABLE coordenador ADD COLUMN IF NOT EXISTS email VARCHAR(254);
ALTER TABLE coordenador ADD COLUMN IF NOT EXISTS senha_hash VARCHAR(255);
ALTER TABLE coordenador ADD COLUMN IF NOT EXISTS ativo BOOLEAN NOT NULL DEFAULT TRUE;
CREATE UNIQUE INDEX IF NOT EXISTS uk_estudante_email_normalizado ON estudante(lower(trim(email))) WHERE email IS NOT NULL;
CREATE UNIQUE INDEX IF NOT EXISTS uk_coordenador_email_normalizado ON coordenador(lower(trim(email))) WHERE email IS NOT NULL;
CREATE TABLE IF NOT EXISTS conquista (
 id BIGSERIAL PRIMARY KEY, atividade_id BIGINT NOT NULL REFERENCES atividade(id) ON DELETE CASCADE,
 titulo VARCHAR(180) NOT NULL, data DATE, descricao TEXT
);
CREATE TABLE IF NOT EXISTS token_redefinicao_senha (
 id BIGSERIAL PRIMARY KEY,
 estudante_id BIGINT REFERENCES estudante(id) ON DELETE CASCADE,
 coordenador_id BIGINT REFERENCES coordenador(id) ON DELETE CASCADE,
 token_hash CHAR(64) NOT NULL UNIQUE, criado_em TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
 expira_em TIMESTAMP WITH TIME ZONE NOT NULL, usado_em TIMESTAMP WITH TIME ZONE,
 CONSTRAINT ck_token_um_usuario CHECK((estudante_id IS NOT NULL AND coordenador_id IS NULL) OR (estudante_id IS NULL AND coordenador_id IS NOT NULL))
);
CREATE INDEX IF NOT EXISTS idx_token_redefinicao_expira ON token_redefinicao_senha(expira_em);
-- N2 usa inativação e deve preservar participações de estudantes.
ALTER TABLE participacao DROP CONSTRAINT IF EXISTS fk_participacao_estudante;
ALTER TABLE participacao ADD CONSTRAINT fk_participacao_estudante FOREIGN KEY(estudante_id) REFERENCES estudante(id) ON DELETE RESTRICT;
COMMIT;

-- Depois da migração, atribua e-mail/hash bcrypt ao primeiro coordenador conforme README.
