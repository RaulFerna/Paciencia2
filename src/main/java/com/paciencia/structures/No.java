package main.java.com.paciencia.structures;

import main.java.com.paciencia.model.Carta;

public class No {
    private Carta informacoes;
    private No next;

    public No(Carta informacoes) {
        if (informacoes == null) {
            throw new IllegalArgumentException("As informações da carta estão vazios");
        }
        this.informacoes = informacoes;
        this.next = next;
    }

    public Carta getInformacoes() {
        return informacoes;
    }

    public No getNext() {
        return next;
    }

    public void setNext(No next) {
        this.next = next;
    }

    public void setInformacoes(Carta informacoes) { //precisa ter a mesma condicional no set pois deve ser verificado se o
        //valor que está sendo alterado não está vazio.
        if (informacoes == null) {
            throw new IllegalArgumentException("As informações da carta estão vazias");
        }
        this.informacoes = informacoes;
    }

    @Override
    public String toString(){
        return informacoes.toString(); // quando for imprimir o nó ele mostra a representação da carta mostrando o que contem.
    }
}
