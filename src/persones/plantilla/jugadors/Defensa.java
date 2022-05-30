package persones.plantilla.jugadors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public class Defensa extends Jugador implements Serializable{
    private int atacsAturats;
    private int balonsRecuperats;
    private static int incentiuAA = 100;
    private static int incentiuBR = 100;

    public int getAtacsAturats() {
        return atacsAturats;
    }

    public void setAtacsAturats(int atacsAturats) {
        this.atacsAturats = atacsAturats;
    }

    public int getBalonsRecuperats() {
        return balonsRecuperats;
    }

    public void setBalonsRecuperats(int balonsRecuperats) {
        this.balonsRecuperats = balonsRecuperats;
    }

    public static int getIncentiuAA() {
        return incentiuAA;
    }

    public static void setIncentiuAA(int incentiuAA) {
        Defensa.incentiuAA = incentiuAA;
    }

    public static int getIncentiuBR() {
        return incentiuBR;
    }

    public static void setIncentiuBR(int incentiuBR) {
        Defensa.incentiuBR = incentiuBR;
    }

    @Override
    public void calcularSouIncentivat() {
        setSouIncentivat(getSouBase() + (atacsAturats * incentiuAA) + (balonsRecuperats * incentiuBR));;
    }

    public Defensa(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, int souBase, boolean esTitular, int atacsAturats,
            int balonsRecuperats) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email, nss, souBase, esTitular);
        this.atacsAturats = atacsAturats;
        this.balonsRecuperats = balonsRecuperats;
        calcularSouIncentivat();
    }

    public Defensa() {
        
    }

    @Override
    public String toString() {
        return super.toString() + "\nAtacs Aturats: " + atacsAturats + "\nBalons Recuperats: " + balonsRecuperats;
    }

    public Defensa altaDefensa() {
        Scanner kb = new Scanner(System.in);
        altaJugador();
        System.out.print("Escriu els atacs aturats: ");
        setAtacsAturats(kb.nextInt());
        System.out.print("Escriur els balons recuperats: ");
        setBalonsRecuperats(kb.nextInt());
        return this;
    }

    @Override
    public void modificar() {
        Scanner kb = new Scanner(System.in);
        super.modificar();
        System.out.print("Atacs aturats: ");
        int aa = kb.nextInt();
        setAtacsAturats(aa);

        System.out.print("Balons recuperats: ");
        int br = kb.nextInt();
        setBalonsRecuperats(br);

        calcularSouIncentivat();
    }
}
