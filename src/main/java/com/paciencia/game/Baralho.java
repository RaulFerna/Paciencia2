package main.java.com.paciencia.game;

import main.java.com.paciencia.model.Carta;
import main.java.com.paciencia.model.NaipeEnum;
import main.java.com.paciencia.structures.Pilha; // Usaremos uma Pilha para armazenar as cartas do baralho

import java.util.Random; // Usaremos Random para o embaralhamento

/**
 * Representa um baralho de 52 cartas e é responsável por criá-las e embaralhá-las.
 * As cartas do baralho serão armazenadas em uma Pilha para facilitar a distribuição (pegar do topo).
 */
public class Baralho {
    private Pilha cartas; // Usamos uma Pilha para o baralho, onde as cartas são retiradas do topo
    private final Random random; // Para o embaralhamento

    /**
     * Construtor do Baralho.
     * Inicializa o baralho com 52 cartas e o embaralha.
     */
    public Baralho() {
        this.cartas = new Pilha();
        this.random = new Random();
        criarBaralho();
        embaralhar();
    }

    /**
     * Cria as 52 cartas únicas e as adiciona à pilha temporariamente.
     */
    private void criarBaralho() {
        // Criar uma pilha temporária para facilitar a criação antes de embaralhar
        Pilha tempPilha = new Pilha();

        for (NaipeEnum naipe : NaipeEnum.values()) { // Percorre todos os naipes (COPAS, OUROS, ESPADAS, PAUS)
            for (int numero = 1; numero <= 13; numero++) { // Percorre os números de 1 (Ás) a 13 (Rei)
                Carta novaCarta = new Carta(numero, naipe, false); // Cartas do baralho inicialmente viradas para baixo
                tempPilha.push(novaCarta); // Adiciona a carta na pilha temporária
            }
        }

        // Transferir as cartas da pilha temporária para o baralho principal de forma desordenada para auxiliar o embaralhamento
        // (Este passo é mais para garantir que a pilha principal comece cheia, o embaralhamento real virá a seguir)
        // O ideal é que o embaralhamento misture a ordem.
        // Para o embaralhamento, vamos extrair todas as cartas para um array temporário e depois recolocá-las.
        // Isso é mais fácil de embaralhar sem ArrayList.

        // Resetar a pilha principal e preparar para o embaralhamento
        this.cartas = new Pilha(); // Garante que a pilha esteja vazia antes de preencher com embaralhamento

        // Precisamos de um array simples para o embaralhamento Fisher-Yates
        // Como não podemos usar ArrayList, vamos criar um array de tamanho fixo 52
        Carta[] arrayCartas = new Carta[52];
        int index = 0;

        // Move as cartas da pilha temporária para o array
        while(!tempPilha.estaVazia()){
            arrayCartas[index++] = tempPilha.pop();
        }

        // Preenche a pilha principal com as cartas do array
        for(int i = 0; i < arrayCartas.length; i++){
            this.cartas.push(arrayCartas[i]);
        }

        // Note: A ordem inicial no 'cartas' não importa muito, pois será embaralhada.
        // O importante é que todas as 52 cartas estejam presentes.
    }

    /**
     * Embaralha as cartas do baralho usando o algoritmo Fisher-Yates (Knuth shuffle).
     * Este algoritmo é eficiente e garante um embaralhamento uniforme.
     */
    public void embaralhar() {
        if (cartas.getTamanho() == 0) {
            // Se o baralho estiver vazio, recriar antes de embaralhar
            criarBaralho(); // Garante que temos as 52 cartas
        }

        // Para embaralhar sem ArrayList, precisamos de um array temporário
        Carta[] tempArray = new Carta[cartas.getTamanho()];
        int currentSize = cartas.getTamanho();

        // 1. Mover todas as cartas da Pilha para o Array Temporário
        for (int i = 0; i < currentSize; i++) {
            tempArray[i] = cartas.pop();
        }

        // 2. Embaralhar o Array Temporário (Algoritmo Fisher-Yates)
        // Percorre o array de trás para frente
        for (int i = currentSize - 1; i > 0; i--) {
            int j = random.nextInt(i + 1); // Gera um índice aleatório entre 0 e i (inclusive)

            // Troca tempArray[i] com tempArray[j]
            Carta temp = tempArray[i];
            tempArray[i] = tempArray[j];
            tempArray[j] = temp;
        }

        // 3. Mover as cartas embaralhadas do Array Temporário de volta para a Pilha
        // Adicionamos de volta na Pilha, mantendo a ordem embaralhada
        for (int i = 0; i < currentSize; i++) {
            cartas.push(tempArray[i]);
        }
        System.out.println("Baralho embaralhado!");
    }

    /**
     * Retira uma carta do topo do baralho.
     * @return A Carta do topo do baralho, ou null se o baralho estiver vazio.
     */
    public Carta puxarCarta() {
        return cartas.pop();
    }

    /**
     * Verifica se o baralho está vazio.
     * @return true se o baralho não tem cartas, false caso contrário.
     */
    public boolean estaVazio() {
        return cartas.estaVazia();
    }

    /**
     * Retorna o número de cartas restantes no baralho.
     * @return O tamanho atual do baralho.
     */
    public int getTamanho() {
        return cartas.getTamanho();
    }

    @Override
    public String toString() {
        return "Baralho: " + cartas.toString();
    }
}