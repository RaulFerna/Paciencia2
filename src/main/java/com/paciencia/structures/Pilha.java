package main.java.com.paciencia.structures;

import main.java.com.paciencia.model.Carta; // Importa a classe Carta

/**
 * Implementação de uma Pilha (Stack) usando nossa classe No personalizada.
 * Segue o princípio LIFO (Last In, First Out).
 */
public class Pilha {
    private No topo; // O topo da pilha
    private int tamanho; // O número de elementos na pilha

    /**
     * Construtor para criar uma Pilha vazia.
     */
    public Pilha() {
        this.topo = null; // Pilha vazia, o topo aponta para null
        this.tamanho = 0;
    }

    /**
     * Adiciona uma Carta ao topo da pilha (operação push).
     * @param carta A Carta a ser adicionada.
     */
    public void push(Carta carta) {
        No novoNo = new No(carta); // Cria um novo nó com a carta
        novoNo.setNext(topo);     // O novo nó aponta para o antigo topo
        topo = novoNo;            // O novo nó se torna o novo topo
        tamanho++;
    }

    /**
     * Remove e retorna a Carta do topo da pilha (operação pop).
     * @return A Carta do topo da pilha, ou null se a pilha estiver vazia.
     */
    public Carta pop() {
        if (estaVazia()) {
            return null; // Não há elementos para remover
        }
        Carta cartaRemovida = topo.getInformacoes(); // Pega a carta do topo
        topo = topo.getNext();                    // O topo agora é o próximo nó
        tamanho--;
        return cartaRemovida;
    }

    /**
     * Retorna a Carta do topo da pilha sem removê-la (operação peek).
     * @return A Carta do topo da pilha, ou null se a pilha estiver vazia.
     */
    public Carta peek() {
        if (estaVazia()) {
            return null; // Pilha vazia
        }
        return topo.getInformacoes(); // Retorna a informação do nó do topo
    }

    /**
     * Verifica se a pilha está vazia.
     * @return true se a pilha não contém elementos, false caso contrário.
     */
    public boolean estaVazia() {
        return topo == null; // Ou return tamanho == 0;
    }

    /**
     * Retorna o número de elementos na pilha.
     * @return O tamanho da pilha.
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * Retorna uma representação em String da Pilha, exibindo as cartas do topo para a base.
     * @return Uma String formatada com as cartas da pilha.
     */
    @Override
    public String toString() {
        if (estaVazia()) {
            return "[Pilha Vazia]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Topo [");
        No atual = topo;
        while (atual != null) {
            sb.append(atual.getInformacoes().toString());
            if (atual.getNext() != null) {
                sb.append(" | "); // Separador para indicar "pilha"
            }
            atual = atual.getNext();
        }
        sb.append("] Base");
        return sb.toString();
    }
}