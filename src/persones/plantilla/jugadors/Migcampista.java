package persones.plantilla.jugadors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public class Migcampista extends Jugador implements Serializable{
    private int assistencies;
    private int balonsRecuperats;
    private static int incentiuAssist = 200;
    private static int incentiuBR = 100;

    @Override
    public void calcularSouIncentivat() {
        setSouIncentivat(getSouBase() + (assistencies * incentiuAssist) + (balonsRecuperats * incentiuBR));;
    }

    public Migcampista(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, int souBase, boolean esTitular, int assistencies,
            int balonsRecuperats) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email, nss, souBase, esTitular);
        this.assistencies = assistencies;
        this.balonsRecuperats = balonsRecuperats;
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

    public int getBalonsRecuperats() {
        return balonsRecuperats;
    }

    public void setBalonsRecuperats(int balonsRecuperats) {
        this.balonsRecuperats = balonsRecuperats;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAssistencies: " + assistencies + "\nBalons Recuperats: " + balonsRecuperats;
    }

    public Migcampista altaMigcampista() {
        Scanner kb = new Scanner(System.in);
        altaJugador();
        System.out.print("Escriu les assistències: ");
        setAssistencies(kb.nextInt());
        System.out.print("Escriu els balons recuperats: ");
        setBalonsRecuperats(kb.nextInt());
        return this;
    }

    @Override
    public void modificar() {
        Scanner kb = new Scanner(System.in);
        super.modificar();

        System.out.print("Nº assistències: ");
        int assistencies = kb.nextInt();
        setAssistencies(assistencies);

        System.out.println("Balons recuperats");
        int br = kb.nextInt();
        setBalonsRecuperats(br);

        calcularSouIncentivat();
    }

    
}
