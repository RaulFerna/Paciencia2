// src/main/java/com/paciencia/structures/Fila.java
package main.java.com.paciencia.structures;

import main.java.com.paciencia.model.Carta;

public class Fila {
    private No head;
    private No tail;
    private int tamanho;

    public Fila() {
        this.head = null;
        this.tail = null;
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
            head = novoNo;
        } else {
            tail.setNext(novoNo);
        }
        tail = novoNo;
        tail.setNext(head); // Mantém a circularidade
        tamanho++;
    }

    public Carta desenfileirar() {
        if (estaVazia()) {
            return null;
        }
        Carta cartaRemovida = head.getInformacoes();
        if (head == tail) { // Se houver apenas um elemento
            head = null;
            tail = null;
        } else { // Se houver mais de um elemento
            head = head.getNext();
            tail.setNext(head); // Mantém a circularidade
        }
        tamanho--;
        return cartaRemovida;
    }

    public Carta espiar() {
        if (estaVazia()) {
            return null;
        }
        return head.getInformacoes();
    }

    @Override
    public String toString() {
        if (estaVazia()) {
            return "[Fila Vazia]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Fila [");
        No atual = head;


        for (int i = 0; i < tamanho; i++) {
            if (atual == null) {
                sb.append("ERRO: Nó nulo inesperado!");
                break;
            }
            sb.append(atual.getInformacoes().toString());

            if (i < tamanho - 1) {
                sb.append(", ");
            }
            atual = atual.getNext(); // Move para o próximo nó
        }
        sb.append("]");
        return sb.toString();
    }
}