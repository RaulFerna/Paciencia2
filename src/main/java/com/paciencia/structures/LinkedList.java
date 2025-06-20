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

    public boolean estaVazia() {
        return tamanho == 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void add(Carta carta) {
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

    public Carta removeLast() {
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

    public Carta remove(int inicio) {
        if (inicio < 0 || inicio >= tamanho) {
            return null;
        }
        if (inicio == 0) {
            Carta cartaRemovida = head.getInformacoes();
            head = head.getNext();
            tamanho--;
            return cartaRemovida;
        }

        No atual = head;
        for (int i = 0; i < inicio - 1; i++) {
            atual = atual.getNext();
        }
        Carta cartaRemovida = atual.getNext().getInformacoes();
        atual.setNext(atual.getNext().getNext()); // Pula o nó a ser removido
        tamanho--;
        return cartaRemovida;
    }

    public Carta get(int inicio) {
        if (inicio < 0 || inicio >= tamanho) {
            return null;
        }
        No atual = head;
        for (int i = 0; i < inicio; i++) {
            atual = atual.getNext();
        }
        return atual.getInformacoes();
    }

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