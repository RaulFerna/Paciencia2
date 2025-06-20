package main.java.com.paciencia.game;

import main.java.com.paciencia.model.Carta;
import main.java.com.paciencia.structures.Pilha;
import main.java.com.paciencia.structures.LinkedList;
import main.java.com.paciencia.model.NaipeEnum.Cor;

/**
 * Classe responsável por conter todas as regras de validação de movimentos do jogo Paciência.
 * Os métodos são estáticos, pois não há estado específico da regra; eles apenas validam dados de entrada.
 */
public class RegrasJogo {

    /**
     * Valida um movimento de uma carta do descarte (ou coluna) para uma pilha final.
     * Regra: Deve ser o Ás inicial ou seguir naipe e ordem crescente.
     * @param cartaOrigem A carta que está sendo movida.
     * @param pilhaDestino A pilha final para onde a carta será movida.
     * @return true se o movimento for válido, false caso contrário.
     */
    public static boolean isMovimentoParaPilhaFinalValido(Carta cartaOrigem, Pilha pilhaDestino) {
        if (cartaOrigem == null) {
            return false; // Não se move carta nula
        }

        if (pilhaDestino.estaVazia()) {
            // Se a pilha final está vazia, só aceita Ás (valor 1)
            return cartaOrigem.getNumeroCarta() == 1;
        } else {
            // Se não está vazia, deve ser a próxima carta do mesmo naipe
            Carta topoDestino = pilhaDestino.peek();
            return cartaOrigem.getNaipe() == topoDestino.getNaipe() &&
                    cartaOrigem.getNumeroCarta() == topoDestino.getNumeroCarta() + 1;
        }
    }

    /**
     * Valida um movimento de uma carta (ou sequência) para uma coluna da mesa.
     * Regra: Deve ser cor oposta e número imediatamente menor que a carta no topo da coluna de destino.
     * Ou ser Rei em coluna vazia.
     * @param cartaInicialSequencia A primeira carta da sequência (ou a única carta) que está sendo movida.
     * @param colunaDestino A coluna da mesa para onde a carta/sequência será movida.
     * @return true se o movimento for válido, false caso contrário.
     */
    public static boolean isMovimentoParaColunaValido(Carta cartaInicialSequencia, LinkedList colunaDestino) {
        if (cartaInicialSequencia == null) {
            return false; // Não se move carta nula
        }

        if (colunaDestino.estaVazia()) {
            // Se a coluna está vazia, só aceita Rei (valor 13)
            return cartaInicialSequencia.getNumeroCarta() == 13;
        } else {
            Carta topoColuna = colunaDestino.get(colunaDestino.getTamanho() - 1);

            // A carta de destino deve estar virada para cima
            if (!topoColuna.isCartaVirada()) {
                return false;
            }
            // Deve ser cor oposta e número imediatamente menor
            return cartaInicialSequencia.getCor() != topoColuna.getCor() &&
                    cartaInicialSequencia.getNumeroCarta() == topoColuna.getNumeroCarta() - 1;
        }
    }

    /**
     * Valida se uma carta pode ser virada para cima.
     * Uma carta pode ser virada se for a última da coluna e estiver virada para baixo.
     * @param coluna A LinkedList que contém a carta.
     * @return true se a última carta da coluna pode ser virada, false caso contrário.
     */
    public static boolean podeVirarCartaNaColuna(LinkedList coluna) {
        if (coluna.estaVazia()) {
            return false;
        }
        Carta ultimaCarta = coluna.get(coluna.getTamanho() - 1);
        return ultimaCarta != null && !ultimaCarta.isCartaVirada();
    }

    // Você pode adicionar mais métodos de regras aqui conforme necessário
}