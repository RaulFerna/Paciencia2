// src/main/java/com/paciencia.structures/LinkedList.java
package main.java.com.paciencia.structures;

import main.java.com.paciencia.model.Carta;

/**
 * Implementação de uma Lista Encadeada Simples.
 * Suporta adição no final, remoção do final, remoção por índice,
 * obtenção por índice e verificação de vazia.
 */
public class LinkedList {
    private No head;
    private int tamanho;

    public LinkedList() {
        this.head = null;
        this.tamanho = 0;
    }

    /**
     * Verifica se a lista está vazia.
     * @return true se a lista não contém elementos, false caso contrário.
     */
    public boolean estaVazia() { // ESTE DEVE SER PUBLIC
        return tamanho == 0; // Ou head == null;
    }

    /**
     * Retorna o número de elementos na lista.
     * @return O número de elementos na lista.
     */
    public int getTamanho() { // ESTE DEVE SER PUBLIC
        return tamanho;
    }

    /**
     * Adiciona uma Carta ao final da lista.
     * @param carta A carta a ser adicionada.
     */
    public void add(Carta carta) { // ESTE DEVE SER PUBLIC
        if (carta == null) {
            throw new IllegalArgumentException("Não é possível adicionar uma carta nula à lista.");
        }
        No novoNo = new No(carta);
        if (head == null) {
            head = novoNo;
        } else {
            No atual = head;
            while (atual.getNext() != null) {
                atual = atual.getNext();
            }
            atual.setNext(novoNo);
        }
        tamanho++;
    }

    /**
     * Remove o último elemento da lista.
     * @return A carta removida, ou null se a lista estiver vazia.
     */
    public Carta removeLast() { // ESTE DEVE SER PUBLIC
        if (estaVazia()) {
            return null;
        }
        if (tamanho == 1) {
            Carta cartaRemovida = head.getInformacoes();
            head = null;
            tamanho--;
            return cartaRemovida;
        }

        No atual = head;
        while (atual.getNext().getNext() != null) {
            atual = atual.getNext();
        }
        Carta cartaRemovida = atual.getNext().getInformacoes();
        atual.setNext(null); // Desconecta o último nó
        tamanho--;
        return cartaRemovida;
    }

    /**
     * Remove o elemento no índice especificado.
     * @param index O índice do elemento a ser removido.
     * @return A carta removida, ou null se o índice for inválido.
     */
    public Carta remove(int index) { // ESTE DEVE SER PUBLIC
        if (index < 0 || index >= tamanho) {
            return null;
        }
        if (index == 0) {
            Carta cartaRemovida = head.getInformacoes();
            head = head.getNext();
            tamanho--;
            return cartaRemovida;
        }

        No atual = head;
        for (int i = 0; i < index - 1; i++) {
            atual = atual.getNext();
        }
        Carta cartaRemovida = atual.getNext().getInformacoes();
        atual.setNext(atual.getNext().getNext()); // Pula o nó a ser removido
        tamanho--;
        return cartaRemovida;
    }

    /**
     * Retorna a carta no índice especificado.
     * @param index O índice da carta a ser retornada.
     * @return A carta no índice, ou null se o índice for inválido.
     */
    public Carta get(int index) { // ESTE DEVE SER PUBLIC
        if (index < 0 || index >= tamanho) {
            return null;
        }
        No atual = head;
        for (int i = 0; i < index; i++) {
            atual = atual.getNext();
        }
        return atual.getInformacoes();
    }

    /**
     * Retorna o Nó no índice especificado.
     * Útil para operações internas ou depuração, se necessário.
     * @param index O índice do Nó a ser retornado.
     * @return O Nó no índice, ou null se o índice for inválido.
     */
    public No getNo(int index) { // ESTE DEVE SER PUBLIC
        if (index < 0 || index >= tamanho) {
            return null;
        }
        No atual = head;
        for (int i = 0; i < index; i++) {
            atual = atual.getNext();
        }
        return atual;
    }

    /**
     * Retorna uma representação em String da lista.
     */
    @Override
    public String toString() {
        if (head == null) {
            return "[Vazio]";
        }
        StringBuilder sb = new StringBuilder();
        No atual = head;
        while (atual != null) {
            sb.append(atual.getInformacoes().toString());
            if (atual.getNext() != null) {
                sb.append(", ");
            }
            atual = atual.getNext();
        }
        return sb.toString();
    }
}