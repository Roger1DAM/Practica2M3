package persones.plantilla.jugadors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public class Porter extends Jugador implements Serializable{
    private int golsAturats;
    private int golsRebuts;
    private static int incentiuGA = 150;


    public int getGolsAturats() {
        return golsAturats;
    }

    public void setGolsAturats(int golsAturats) {
        this.golsAturats = golsAturats;
    }

    public int getGolsRebuts() {
        return golsRebuts;
    }

    public void setGolsRebuts(int golsRebuts) {
        this.golsRebuts = golsRebuts;
    }

    public static int getIncentiuGA() {
        return incentiuGA;
    }

    public static void setIncentiuGA(int incentiuGA) {
        Porter.incentiuGA = incentiuGA;
    }

    @Override
    public void calcularSouIncentivat() {
        setSouIncentivat(getSouBase() + (getGolsAturats() * incentiuGA));;
    }

    public Porter(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, int souBase, boolean esTitular, int golsAturats,
            int golsRebuts) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email, nss, souBase, esTitular);
        this.golsAturats = golsAturats;
        this.golsRebuts = golsRebuts;
        calcularSouIncentivat();
    }

    @Override
    public String toString() {
        return super.toString() + "\nGols Aturats: " + golsAturats + "\nGols Rebuts: " + golsRebuts;
    }

    public Porter() {
        
    }

    public Porter altaPorter() {
        Scanner kb = new Scanner(System.in);
        altaJugador();
        System.out.print("Escriu els gols aturats: ");
        setGolsAturats(kb.nextInt());
        System.out.print("Escriu els gols rebuts: ");
        setGolsRebuts(kb.nextInt());
        return this;
    }

    @Override
    public void modificar() {
        Scanner kb = new Scanner(System.in);
        super.modificar();

        System.out.print("Gols aturats: ");
        int ga = kb.nextInt();
        setGolsAturats(ga);

        System.out.print("Gols rebuts: ");
        int gr = kb.nextInt();
        setGolsRebuts(gr);
        
        calcularSouIncentivat();
    }
}


