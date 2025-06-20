package main.java.com.paciencia.game;

import main.java.com.paciencia.model.Carta;
import main.java.com.paciencia.model.NaipeEnum;
import main.java.com.paciencia.structures.Pilha; // Usaremos uma Pilha para armazenar as cartas do baralho

import java.util.Random; // Usaremos Random para o embaralhamento

public class Baralho {
    private Pilha cartas;
    private final Random embaralhar; // O Random é usado para que possa ser gerado um valor aleatório, sendo usado para embaralhar as cartas

    public Baralho() {
        this.cartas = new Pilha();
        this.embaralhar = new Random();
        criarBaralho();
        embaralhar();
    }

    private void criarBaralho() { //Usado para criar todas as cartas do baralho
        Pilha tempPilha = new Pilha();// Está criando uma pilha temporária para facilitar a criação antes de embaralhar

        for (NaipeEnum naipe : NaipeEnum.values()) { // for para percorrer as cartas

            for (int numero = 1; numero <= 13; numero++) { // Percorre os números de 1 (Ás) a 13 (Rei)
                Carta novaCarta = new Carta(numero, naipe, false); // Aqui está sendo implementado uma condição para as cartas que estão viradas.
                tempPilha.push(novaCarta);
            }
        }
        this.cartas = new Pilha(); // Garante que a pilha esteja vazia antes de preencher com embaralhamento

        Carta[] arrayCartas = new Carta[52];
        int inicio = 0;

        while (!tempPilha.estaVazia()) {// Move as cartas da pilha temporária para o array
            arrayCartas[inicio++] = tempPilha.remover();
        }

        for (int i = 0; i < arrayCartas.length; i++) {// Preenche a pilha principal com as cartas do array
            this.cartas.push(arrayCartas[i]);
        }

    }

    public void embaralhar() {
        if (cartas.getTamanho() == 0) {
            criarBaralho();
        }
        Carta[] tempArray = new Carta[cartas.getTamanho()]; // Para embaralhar sem ArrayList, precisa de um array temporário, sendo mais eficiente
        int currentSize = cartas.getTamanho();

        for (int i = 0; i < currentSize; i++) { //Aqui move as cartas para o array temporário
            tempArray[i] = cartas.remover();
        }

        for (int i = currentSize - 1; i > 0; i--) {
            int j = embaralhar.nextInt(i + 1); // Gera um índice aleatório entre 0 e i (inclusive)
            Carta temp = tempArray[i];
            tempArray[i] = tempArray[j];
            tempArray[j] = temp;
        }

        for (int i = 0; i < currentSize; i++) {
            cartas.push(tempArray[i]);
        }
        System.out.println("Baralho embaralhado!");
    }

    public Carta puxarCarta() {
        return cartas.remover();
    }

    public boolean estaVazio() {
        return cartas.estaVazia();
    }

    public int getTamanho() {
        return cartas.getTamanho();
    }

    @Override
    public String toString() {
        return "Baralho: " + cartas.toString();
    }
}