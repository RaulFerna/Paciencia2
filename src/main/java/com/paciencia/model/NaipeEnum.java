package main.java.com.paciencia.model;

public enum NaipeEnum {
    COPAS("Copas", Cor.VERMELHA),
    PAUS("Paus" , Cor.PRETA),
    ESPADAS("Espadas", Cor.PRETA),
    OUROS("Ouros", Cor.VERMELHA);

    private final Cor cor; // estão final para que não sejam alterados
    private final String nome;

    NaipeEnum(String nome, Cor cor) {
        this.cor = cor;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Cor getCor() {
        return cor;
    }

    @Override
    public String toString(){ //é um metodo que o retorno do tipo dele é uma String
        return nome;
    }

    public enum Cor{ //Classe auxiliar para classe enum, fornecendo as cores
        VERMELHA,
        PRETA;
    }
}
