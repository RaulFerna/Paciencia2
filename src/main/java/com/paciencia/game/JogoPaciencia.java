package main.java.com.paciencia.game;

import main.java.com.paciencia.model.Carta;
import main.java.com.paciencia.model.NaipeEnum;
import main.java.com.paciencia.model.NaipeEnum.Cor;
import main.java.com.paciencia.structures.Fila;
import main.java.com.paciencia.structures.LinkedList;
import main.java.com.paciencia.structures.No;
import main.java.com.paciencia.structures.Pilha;

import java.util.InputMismatchException;
import java.util.Scanner;

// Importa a classe RegrasJogo que contém a lógica de validação
import main.java.com.paciencia.game.RegrasJogo;

/**
 * Classe principal que gerencia a lógica do jogo de Paciência.
 * Orquestra a distribuição de cartas, movimentações e o estado do tabuleiro.
 */
public class JogoPaciencia {
    private Baralho baralho;
    private Fila filaDeCompra;
    private Pilha pilhaDescarteCompra;
    private LinkedList[] colunas;
    private Pilha[] pilhasFinais;
    private Scanner scanner;

    private static final int NUM_COLUNAS = 7;
    private static final int NUM_PILHAS_FINAIS = 4;

    public JogoPaciencia() {
        this.baralho = new Baralho();
        this.filaDeCompra = new Fila();
        this.pilhaDescarteCompra = new Pilha();
        this.colunas = new LinkedList[NUM_COLUNAS];
        for (int i = 0; i < NUM_COLUNAS; i++) {
            this.colunas[i] = new LinkedList();
        }
        this.pilhasFinais = new Pilha[NUM_PILHAS_FINAIS];
        for (int i = 0; i < NUM_PILHAS_FINAIS; i++) {
            this.pilhasFinais[i] = new Pilha();
        }
        this.scanner = new Scanner(System.in);
    }

    public void iniciarNovoJogo() {
        System.out.println("--- INICIANDO NOVO JOGO DE PACIÊNCIA ---");
        this.baralho = new Baralho();
        this.filaDeCompra = new Fila();
        this.pilhaDescarteCompra = new Pilha();

        for (int i = 0; i < NUM_COLUNAS; i++) {
            this.colunas[i] = new LinkedList();
        }
        for (int i = 0; i < NUM_PILHAS_FINAIS; i++) {
            this.pilhasFinais[i] = new Pilha();
        }

        System.out.println("Distribuindo cartas para as colunas...");
        for (int i = 0; i < NUM_COLUNAS; i++) {
            for (int j = 0; j <= i; j++) {
                Carta carta = baralho.puxarCarta();
                if (carta != null) {
                    carta.setCartaVirada(j == i);
                    colunas[i].add(carta);
                }
            }
        }

        System.out.println("Preparando monte de compra...");
        while (!baralho.estaVazio()) {
            Carta carta = baralho.puxarCarta();
            if (carta != null) {
                carta.setCartaVirada(false);
                filaDeCompra.enfileirar(carta);
            }
        }
        System.out.println("Jogo iniciado! Boa sorte!");
        exibirEstadoDoJogo();
    }

    public void exibirEstadoDoJogo() {
        System.out.println("\n--- ESTADO ATUAL DO JOGO ---");

        System.out.print("Pilhas Finais: ");
        NaipeEnum[] naipesBase = {NaipeEnum.COPAS, NaipeEnum.OUROS, NaipeEnum.ESPADAS, NaipeEnum.PAUS};
        for (int i = 0; i < NUM_PILHAS_FINAIS; i++) {
            System.out.printf(" %s: %s", naipesBase[i].getNome(), pilhasFinais[i].estaVazia() ? "[Vazio]" : pilhasFinais[i].peek().getValorNominal());
        }
        System.out.println();

        System.out.print("Monte de Compra: " + (filaDeCompra.estaVazia() ? "[Vazio]" : "[###]"));
        System.out.println("   Descarte (Topo): " + (pilhaDescarteCompra.estaVazia() ? "[Vazio]" : pilhaDescarteCompra.peek().toString()));


        System.out.println("\nColunas da Mesa:");
        int alturaMax = 0;
        for (int i = 0; i < NUM_COLUNAS; i++) {
            if (colunas[i].getTamanho() > alturaMax) {
                alturaMax = colunas[i].getTamanho();
            }
        }

        System.out.print("     ");
        for (int i = 0; i < NUM_COLUNAS; i++) {
            System.out.printf("Col %d        ", i + 1);
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------");


        for (int row = 0; row < alturaMax; row++) {
            System.out.printf("%2d | ", row);
            for (int col = 0; col < NUM_COLUNAS; col++) {
                Carta cartaNaPosicao = colunas[col].get(row);
                if (cartaNaPosicao != null) {
                    String cartaStr = cartaNaPosicao.toString();
                    System.out.printf("%-12s", cartaStr);
                } else {
                    System.out.printf("%-12s", "");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
    }

    public boolean mostrarMenuJogo() {
        System.out.println("\n--- MENU DO JOGO ---");
        System.out.println("a. Movimentar carta do Descarte da Compra para Pilha Final");
        System.out.println("b. Mover carta do Descarte da Compra para Coluna da Mesa");
        System.out.println("c. Movimentar carta da Coluna da Mesa para Pilha Final");
        System.out.println("d. Movimentar carta de uma Coluna da Mesa para outra Coluna da Mesa");
        System.out.println("e. Comprar próxima carta da Fila de Compra (Virar carta do monte)");
        System.out.println("f. Reiniciar o jogo");
        System.out.println("g. Ver estado atual do jogo");
        System.out.println("h. Sair do jogo");
        System.out.print("Escolha uma opção: ");

        String opcao = scanner.nextLine().toLowerCase();

        switch (opcao) {
            case "a":
                moverDescarteParaPilhaFinal();
                break;
            case "b":
                moverDescarteParaColuna();
                break;
            case "c":
                moverColunaParaPilhaFinal();
                break;
            case "d":
                moverColunaParaColuna();
                break;
            case "e":
                comprarCartaFila();
                break;
            case "f":
                System.out.println("Reiniciando o jogo...");
                iniciarNovoJogo();
                break;
            case "g":
                exibirEstadoDoJogo();
                break;
            case "h":
                System.out.println("Saindo do jogo. Até a próxima!");
                return false;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }

        if (verificarVitoria()) {
            System.out.println("\nPARABÉNS! VOCÊ GANHOU O JOGO DE PACIÊNCIA!");
            return false;
        }
        return true;
    }

    public boolean verificarVitoria() {
        int cartasNasPilhasFinais = 0;
        for (Pilha pilha : pilhasFinais) {
            cartasNasPilhasFinais += pilha.getTamanho();
        }
        return cartasNasPilhasFinais == 52;
    }

    private int obterIndiceColuna(String prompt) {
        int coluna = -1;
        while (coluna < 1 || coluna > NUM_COLUNAS) {
            try {
                System.out.print(prompt + " (1-" + NUM_COLUNAS + "): ");
                coluna = scanner.nextInt();
                if (coluna < 1 || coluna > NUM_COLUNAS) {
                    System.out.println("Coluna inválida. Digite um número entre 1 e " + NUM_COLUNAS + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }
        return coluna - 1;
    }

    private int obterIndicePilhaFinal(String prompt) {
        int pilha = -1;
        while (pilha < 1 || pilha > NUM_PILHAS_FINAIS) {
            try {
                System.out.print(prompt + " (1-" + NUM_PILHAS_FINAIS + " - Copas, Ouros, Espadas, Paus): ");
                pilha = scanner.nextInt();
                if (pilha < 1 || pilha > NUM_PILHAS_FINAIS) {
                    System.out.println("Pilha final inválida. Digite um número entre 1 e " + NUM_PILHAS_FINAIS + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }
        return pilha - 1;
    }

    private int obterIndiceCartaNaColuna(LinkedList coluna, String prompt) {
        int indice = -1;
        while (indice < 0 || indice >= coluna.getTamanho()) {
            try {
                System.out.print(prompt + " (0-" + (coluna.getTamanho() - 1) + "): ");
                indice = scanner.nextInt();
                if (indice < 0 || indice >= coluna.getTamanho()) {
                    System.out.println("Índice de carta inválido. Digite um número entre 0 e " + (coluna.getTamanho() - 1) + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.next();
            } finally {
                scanner.nextLine();
            }
        }
        return indice;
    }


    private void moverDescarteParaPilhaFinal() {
        if (pilhaDescarteCompra.estaVazia()) {
            System.out.println("A pilha de descarte está vazia. Não há cartas para mover.");
            return;
        }

        Carta cartaOrigem = pilhaDescarteCompra.peek();
        System.out.println("Carta no descarte: " + cartaOrigem);

        int destPilhaIndex = obterIndicePilhaFinal("Para qual pilha final deseja mover?");
        Pilha pilhaDestino = pilhasFinais[destPilhaIndex];

        // Chamada ao método de validação da classe RegrasJogo
        boolean movimentoValido = RegrasJogo.isMovimentoParaPilhaFinalValido(cartaOrigem, pilhaDestino);

        if (movimentoValido) {
            pilhaDescarteCompra.pop();
            pilhaDestino.push(cartaOrigem);
            System.out.println("Carta movida para a pilha final " + (destPilhaIndex + 1) + " com sucesso!");
        } else {
            System.out.println("Movimento inválido: Carta não pode ser movida para esta pilha final.");
        }
        exibirEstadoDoJogo();
    }

    private void moverDescarteParaColuna() {
        if (pilhaDescarteCompra.estaVazia()) {
            System.out.println("A pilha de descarte está vazia. Não há cartas para mover.");
            return;
        }

        Carta cartaOrigem = pilhaDescarteCompra.peek();
        System.out.println("Carta no descarte: " + cartaOrigem);

        int destColunaIndex = obterIndiceColuna("Para qual coluna da mesa deseja mover?");
        LinkedList colunaDestino = colunas[destColunaIndex];

        // Chamada ao método de validação da classe RegrasJogo
        boolean movimentoValido = RegrasJogo.isMovimentoParaColunaValido(cartaOrigem, colunaDestino);

        if (movimentoValido) {
            pilhaDescarteCompra.pop();
            colunaDestino.add(cartaOrigem);
            System.out.println("Carta movida para a coluna " + (destColunaIndex + 1) + " com sucesso!");
        } else {
            System.out.println("Movimento inválido: Carta não pode ser movida para esta coluna.");
        }
        exibirEstadoDoJogo();
    }

    private void moverColunaParaPilhaFinal() {
        int origemColunaIndex = obterIndiceColuna("De qual coluna deseja mover a carta?");
        LinkedList colunaOrigem = colunas[origemColunaIndex];

        if (colunaOrigem.estaVazia()) {
            System.out.println("A coluna " + (origemColunaIndex + 1) + " está vazia.");
            return;
        }

        Carta cartaOrigem = colunaOrigem.get(colunaOrigem.getTamanho() - 1);
        if (!cartaOrigem.isCartaVirada()) {
            System.out.println("A última carta da coluna " + (origemColunaIndex + 1) + " está virada para baixo.");
            return;
        }
        System.out.println("Carta selecionada: " + cartaOrigem);

        int destPilhaIndex = obterIndicePilhaFinal("Para qual pilha final deseja mover?");
        Pilha pilhaDestino = pilhasFinais[destPilhaIndex];

        // Chamada ao método de validação da classe RegrasJogo
        boolean movimentoValido = RegrasJogo.isMovimentoParaPilhaFinalValido(cartaOrigem, pilhaDestino);

        if (movimentoValido) {
            colunaOrigem.removeLast();

            // Chamada ao método de validação da classe RegrasJogo para virar carta
            if (RegrasJogo.podeVirarCartaNaColuna(colunaOrigem)) {
                Carta novaUltima = colunaOrigem.get(colunaOrigem.getTamanho() - 1);
                novaUltima.setCartaVirada(true);
                System.out.println("Carta virada para cima na coluna " + (origemColunaIndex + 1) + ": " + novaUltima);
            }
            pilhaDestino.push(cartaOrigem);
            System.out.println("Carta movida para a pilha final " + (destPilhaIndex + 1) + " com sucesso!");
        } else {
            System.out.println("Movimento inválido: Carta não pode ser movida para esta pilha final.");
        }
        exibirEstadoDoJogo();
    }

    private void moverColunaParaColuna() {
        int origemColunaIndex = obterIndiceColuna("De qual coluna deseja mover cartas?");
        LinkedList colunaOrigem = colunas[origemColunaIndex];

        if (colunaOrigem.estaVazia()) {
            System.out.println("A coluna " + (origemColunaIndex + 1) + " está vazia.");
            return;
        }

        System.out.println("Cartas visíveis na Coluna " + (origemColunaIndex + 1) + ":");
        int primeiroIndiceVisivel = -1;
        for (int i = 0; i < colunaOrigem.getTamanho(); i++) {
            if (colunaOrigem.get(i) != null && colunaOrigem.get(i).isCartaVirada()) {
                primeiroIndiceVisivel = i;
                break;
            }
        }

        if (primeiroIndiceVisivel == -1) {
            System.out.println("Não há cartas visíveis nesta coluna para mover.");
            return;
        }

        for (int i = primeiroIndiceVisivel; i < colunaOrigem.getTamanho(); i++) {
            System.out.println("  " + i + ": " + colunaOrigem.get(i).toString());
        }

        int indiceInicialSequencia = obterIndiceCartaNaColuna(colunaOrigem, "Índice da primeira carta da sequência que deseja mover:");

        Carta cartaInicialSequencia = colunaOrigem.get(indiceInicialSequencia);
        if (cartaInicialSequencia == null || !cartaInicialSequencia.isCartaVirada()) {
            System.out.println("Movimento inválido: A carta selecionada não é visível ou não existe neste índice.");
            return;
        }

        int destColunaIndex = obterIndiceColuna("Para qual coluna da mesa deseja mover?");
        LinkedList colunaDestino = colunas[destColunaIndex];

        // Chamada ao método de validação da classe RegrasJogo
        boolean movimentoValido = RegrasJogo.isMovimentoParaColunaValido(cartaInicialSequencia, colunaDestino);

        if (movimentoValido) {
            LinkedList tempSeqList = new LinkedList();
            int tamanhoSequencia = colunaOrigem.getTamanho() - indiceInicialSequencia;

            for (int i = 0; i < tamanhoSequencia; i++) {
                tempSeqList.add(colunaOrigem.remove(indiceInicialSequencia));
            }

            for (int i = 0; i < tempSeqList.getTamanho(); i++) {
                colunaDestino.add(tempSeqList.get(i));
            }

            // Chamada ao método de validação da classe RegrasJogo para virar carta
            if (RegrasJogo.podeVirarCartaNaColuna(colunaOrigem)) {
                Carta novaUltima = colunaOrigem.get(colunaOrigem.getTamanho() - 1);
                novaUltima.setCartaVirada(true);
                System.out.println("Carta virada para cima na coluna " + (origemColunaIndex + 1) + ": " + novaUltima);
            }
            System.out.println("Cartas movidas da coluna " + (origemColunaIndex + 1) + " para a coluna " + (destColunaIndex + 1) + " com sucesso!");

        } else {
            System.out.println("Movimento inválido: Sequência não pode ser movida para esta coluna.");
        }
        exibirEstadoDoJogo();
    }

    private void comprarCartaFila() {
        if (!filaDeCompra.estaVazia()) {
            Carta cartaComprada = filaDeCompra.desenfileirar();
            cartaComprada.setCartaVirada(true);
            pilhaDescarteCompra.push(cartaComprada);
            System.out.println("Você comprou a carta: " + cartaComprada);
        } else if (!pilhaDescarteCompra.estaVazia()) {
            System.out.println("Fila de compra vazia. Retornando cartas do descarte para a fila.");
            while (!pilhaDescarteCompra.estaVazia()) {
                Carta carta = pilhaDescarteCompra.pop();
                carta.setCartaVirada(false);
                filaDeCompra.enfileirar(carta);
            }
            if (!filaDeCompra.estaVazia()) {
                Carta cartaComprada = filaDeCompra.desenfileirar();
                cartaComprada.setCartaVirada(true);
                pilhaDescarteCompra.push(cartaComprada);
                System.out.println("Você comprou a carta: " + cartaComprada);
            } else {
                System.out.println("Não há mais cartas para comprar no monte.");
            }
        } else {
            System.out.println("Não há mais cartas para comprar no monte.");
        }
        exibirEstadoDoJogo();
    }
}