// src/main/java/com/paciencia/structures/Fila.java
package main.java.com.paciencia.structures;

import main.java.com.paciencia.model.Carta;

// Importe o No se não estiver no mesmo pacote
// import main.java.com.paciencia.structures.No;


/**
 * Implementação de uma Fila circular simples usando nós.
 */
public class Fila {
    private No primeiro;
    private No ultimo;
    private int tamanho;

    public Fila() {
        this.primeiro = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void enfileirar(Carta carta) {
        if (carta == null) {
            throw new IllegalArgumentException("Não é possível enfileirar uma carta nula.");
        }
        No novoNo = new No(carta);
        if (estaVazia()) {
            primeiro = novoNo;
        } else {
            ultimo.setNext(novoNo);
        }
        ultimo = novoNo;
        ultimo.setNext(primeiro); // Mantém a circularidade
        tamanho++;
    }

    public Carta desenfileirar() {
        if (estaVazia()) {
            // System.out.println("Fila vazia, não é possível desenfileirar."); // Para debug
            return null;
        }
        Carta cartaRemovida = primeiro.getInformacoes();
        if (primeiro == ultimo) { // Se houver apenas um elemento
            primeiro = null;
            ultimo = null;
        } else { // Se houver mais de um elemento
            primeiro = primeiro.getNext();
            ultimo.setNext(primeiro); // Mantém a circularidade
        }
        tamanho--;
        return cartaRemovida;
    }

    public Carta espiar() { // peek() da Fila
        if (estaVazia()) {
            return null;
        }
        return primeiro.getInformacoes();
    }

    // --- MÉTODO toString() CORRIGIDO ---
    @Override
    public String toString() {
        if (estaVazia()) {
            return "[Fila Vazia]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Fila [");
        No atual = primeiro;

        // Itera 'tamanho' vezes para garantir que todos os elementos são visitados
        // e para evitar loops infinitos em caso de ponteiros inconsistentes.
        for (int i = 0; i < tamanho; i++) {
            if (atual == null) {
                // Isso não deveria acontecer em uma Fila corretamente mantida
                sb.append("ERRO: Nó nulo inesperado!");
                break;
            }
            sb.append(atual.getInformacoes().toString());

            // Adiciona vírgula se não for o último elemento que estamos processando
            if (i < tamanho - 1) {
                sb.append(", ");
            }
            atual = atual.getNext(); // Move para o próximo nó
        }
        sb.append("]");
        return sb.toString();
    }
}