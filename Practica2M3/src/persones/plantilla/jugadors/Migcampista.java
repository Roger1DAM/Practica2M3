package persones.plantilla.jugadors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public class Migcampista extends Jugador implements Serializable{
    private int assistencies;
    private int balonsRecuprats;
    private static int incentiuAssist = 200;
    private static int incentiuBR = 100;

    @Override
    public void calcularSouIncentivat() {
        setSouIncentivat(getSouBase() + (assistencies * incentiuAssist) + (balonsRecuprats * incentiuBR));;
    }

    public Migcampista(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, int souBase, boolean esTitular, int assistencies,
            int balonsRecuprats) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email, nss, souBase, esTitular);
        this.assistencies = assistencies;
        this.balonsRecuprats = balonsRecuprats;
        calcularSouIncentivat();
    }

    public Migcampista() {
        
    }

    public int getAssistencies() {
        return assistencies;
    }

    public void setAssistencies(int assistencies) {
        this.assistencies = assistencies;
    }

    public int getBalonsRecuprats() {
        return balonsRecuprats;
    }

    public void setBalonsRecuprats(int balonsRecuprats) {
        this.balonsRecuprats = balonsRecuprats;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAssistencies: " + assistencies + "\nBalons Recuprats: " + balonsRecuprats;
    }

    public Migcampista altaMigcampista() {
        Scanner kb = new Scanner(System.in);
        altaJugador();
        System.out.print("Escriu les assistències: ");
        setAssistencies(kb.nextInt());
        System.out.print("Escriu els balons recuperats: ");
        setBalonsRecuprats(kb.nextInt());
        return this;
    }

    
}
