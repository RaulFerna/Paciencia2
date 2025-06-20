package main.java.com.paciencia.game;
public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando Aplicação Jogo de Paciência...");
        JogoPaciencia jogo = new JogoPaciencia();
        jogo.iniciarNovoJogo();

        boolean continuarJogo = true;
        while (continuarJogo) {
            continuarJogo = jogo.mostrarMenuJogo();
        }

        System.out.println("Fim do Jogo. Obrigado por jogar!");
    }
}