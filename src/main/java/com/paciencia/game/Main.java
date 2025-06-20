// src/main/java/com/paciencia/game/Main.java
package main.java.com.paciencia.game;

// Importa a classe JogoPaciencia que contém a lógica do jogo
// Se Main.java estiver no mesmo pacote de JogoPaciencia, o import é opcional, mas boa prática.
// import main.java.com.paciencia.game.JogoPaciencia; 

/**
 * Classe principal para iniciar o jogo de Paciência.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando Aplicação Jogo de Paciência...");
        JogoPaciencia jogo = new JogoPaciencia();
        jogo.iniciarNovoJogo();

        boolean continuarJogo = true;
        while (continuarJogo) {
            continuarJogo = jogo.mostrarMenuJogo();
            // O loop continua enquanto mostrarMenuJogo retornar true
            // Retorna false quando o usuário escolhe sair ou o jogo é ganho.
        }

        System.out.println("Fim do Jogo. Obrigado por jogar!");
    }
}