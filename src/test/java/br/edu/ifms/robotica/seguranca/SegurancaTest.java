package br.edu.ifms.robotica.seguranca;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SegurancaTest {
    @Test
    public void bcryptVerificaSomenteASenhaCorreta() {
        String hash = SenhaUtil.gerarHash("senha-segura-123");
        assertTrue(SenhaUtil.verificar("senha-segura-123", hash));
        assertFalse(SenhaUtil.verificar("senha-incorreta", hash));
        assertFalse(hash.contains("senha-segura-123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejeitaSenhaCurta() {
        SenhaUtil.gerarHash("curta");
    }

    @Test
    public void tokensSaoAleatoriosEArmazenaveisComoHash() {
        String primeiro = TokenUtil.gerar();
        String segundo = TokenUtil.gerar();
        assertNotEquals(primeiro, segundo);
        assertNotEquals(primeiro, TokenUtil.hash(primeiro));
        assertTrue(TokenUtil.hash(primeiro).matches("[0-9a-f]{64}"));
    }
}
