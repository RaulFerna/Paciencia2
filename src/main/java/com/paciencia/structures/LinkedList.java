package main.java.com.paciencia.structures;

import main.java.com.paciencia.model.Carta;

public class LinkedList {
    private No head;
    private int size;

    public LinkedList() {
        this.head = head;
        this.size = 0;
    }

    public boolean headEmpty() {
        return head == null;
    }

    void add(Carta carta) {
        No newNo = new No(carta); // carta a ser adicionada.
        if (headEmpty()) { // se a head (cabeça) estiver empty (vazia) o novo nó se torna a head.
            head = newNo;
        } else {
            No noCourrent = head;
            while (noCourrent.getNext() != null) {
                noCourrent = noCourrent.getNext();
            }
            noCourrent.setNext(newNo); //o tail aponta para o head.
        }
        size++;
    }

    void addNewHead(Carta carta) { // metodo para poder adicionar um nó no inicio da lista.
        No newNode = new No(carta); //declarando um novo nó
        newNode.setNext(head); // alterando a head para o novo nó
        head = newNode; // atualizando o head que recebe o novo nó
        size++;
    }

    Carta remove(int numberCarta) {
        if (numberCarta < 0 || numberCarta >= size) {
            System.out.println("Valores inválidos, necessita ser entre 0 e " + size);
            return null; //para indice fora dos limites.
        }
        Carta removedCarta;//variavel para notificar uma remoção de carta que recebe a classe Carta como objeto.
        if (numberCarta == 0) {
            removedCarta = head.getInformacoes();
            head = head.getNext();
        }
        else{
            No noPrevius = head;
            for (int i = 0; i < numberCarta -1; i++){
                noPrevius = noPrevius.getNext();
            }
            No noCourrent = noPrevius.getNext();
            removedCarta = noCourrent.getInformacoes();
            noPrevius.setNext(noCourrent.getNext());
        }
        size--;
        return removedCarta;
    }

    //falta metodos de remover a ultima carta, implementar o get do nó, implementar um metodo get que tem a carta como onbjeto


    public No getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }
}
