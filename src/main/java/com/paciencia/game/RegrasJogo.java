package main.java.com.paciencia.game;

import main.java.com.paciencia.model.Carta;
import main.java.com.paciencia.structures.Pilha;
import main.java.com.paciencia.structures.LinkedList;

public class RegrasJogo {
    public static boolean isMovimentoParaPilhaFinalValido(Carta cartaOrigem, Pilha pilhaDestino) {
        if (cartaOrigem == null) {
            return false; // Não se move carta nula
        }

        if (pilhaDestino.estaVazia()) {
            return cartaOrigem.getNumeroCarta() == 1;
        } else {
            Carta topoDestino = pilhaDestino.peek();
            return cartaOrigem.getNaipe() == topoDestino.getNaipe() &&
                    cartaOrigem.getNumeroCarta() == topoDestino.getNumeroCarta() + 1;
        }
    }

    public static boolean isMovimentoParaColunaValido(Carta cartaInicialSequencia, LinkedList colunaDestino) {
        if (cartaInicialSequencia == null) {
            return false; // Verifica se está a carta está como nula, não permitindo mover
        }

        if (colunaDestino.estaVazia()) { // verifica se a coluna estiver vazia, aceitando apenas o rei
            return cartaInicialSequencia.getNumeroCarta() == 13;
        } else {
            Carta topoColuna = colunaDestino.get(colunaDestino.getTamanho() - 1);
            if (!topoColuna.isCartaVirada()) {
                return false;
            }
            return cartaInicialSequencia.getCor() != topoColuna.getCor() &&
                    cartaInicialSequencia.getNumeroCarta() == topoColuna.getNumeroCarta() - 1;
        }
    }
    public static boolean podeVirarCartaNaColuna(LinkedList coluna) {
        if (coluna.estaVazia()) {
            return false;
        }
        Carta ultimaCarta = coluna.get(coluna.getTamanho() - 1);
        return ultimaCarta != null && !ultimaCarta.isCartaVirada();
    }
}