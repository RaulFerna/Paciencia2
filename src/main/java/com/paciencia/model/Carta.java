package main.java.com.paciencia.model;

public class Carta {
    private final NaipeEnum naipe;
    private final int numeroCarta;
    private boolean cartaVirada;

    public Carta(int numeroCarta, NaipeEnum naipe, boolean cartaVirada) { // foram passadas condicionais no construtor para
                                                                      // que não fosse inserido valores errados ou nulos.
        if(numeroCarta < 1 || numeroCarta > 13){
            throw new IllegalArgumentException("O numero das cartas devem ser entre 1 e 13.");
        }
        if (naipe == null){
            throw new IllegalArgumentException("O naipe da carta não pode ser nulo.");
        }
        this.numeroCarta = numeroCarta;
        this.cartaVirada = cartaVirada;
        this.naipe = naipe;
    }

    public String getValorNominal(){
        switch (numeroCarta){
            case 1: return "Ás";
            case 11: return "Valete";
            case 12: return "Dama";
            case 13: return "Reis";
            default: return String.valueOf(numeroCarta); // retorna o numero das cartas, por isso o String valueOf
        }
    }

    @Override
    public String toString(){
        if(!cartaVirada){
            return "[xx]"; // carta virada para baixo
        }
        else{
            return getValorNominal() + " de " + naipe.getNome(); //ele (metodo) passa o valor que a pessoa escolheu e fala o naipe da carta
        }
    }

    public NaipeEnum getNaipe() {
        return naipe;
    }

    public NaipeEnum.Cor getCor(){
        return naipe.getCor();
    }

    public int getNumeroCarta() {
        return numeroCarta;
    }

    public boolean isCartaVirada() {
        return cartaVirada;
    }

    public void setCartaVirada(boolean cartaVirada) {
        this.cartaVirada = cartaVirada;
    }
}
