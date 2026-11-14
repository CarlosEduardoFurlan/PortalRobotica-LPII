package br.edu.ifms.robotica.seguranca;

import org.mindrot.jbcrypt.BCrypt;

public final class SenhaUtil {
    public static final int TAMANHO_MINIMO = 8;

    private SenhaUtil() {
    }

    public static String gerarHash(String senha) {
        validarNovaSenha(senha);
        return BCrypt.hashpw(senha, BCrypt.gensalt(12));
    }

    public static boolean verificar(String senha, String hash) {
        if (senha == null || hash == null || hash.isBlank()) {
            return false;
        }
        try {
            return BCrypt.checkpw(senha, hash);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static void validarNovaSenha(String senha) {
        if (senha == null || senha.length() < TAMANHO_MINIMO) {
            throw new IllegalArgumentException("erro.senha.tamanho");
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Uso: SenhaUtil <senha>");
            return;
        }
        System.out.println(gerarHash(args[0]));
    }
}
