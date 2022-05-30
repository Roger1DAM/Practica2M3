package persones.plantilla.jugadors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public class Davanter extends Jugador implements Serializable{
    private int gols;
    private int balonsRecuperats;
    private static int incentiuGols = 500;
    private static int incentiuBR = 100;

    @Override
    public void calcularSouIncentivat() {
        setSouIncentivat(getSouBase() + (gols * incentiuGols) + (balonsRecuperats * incentiuBR));
        
    }

    public Davanter(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, int souBase, boolean esTitular, int gols,
            int balonsRecuperats) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email, nss, souBase, esTitular);
        this.gols = gols;
        this.balonsRecuperats = balonsRecuperats;
        calcularSouIncentivat();
    }

    public Davanter() {

    }

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    public int getBalonsRecuperats() {
        return balonsRecuperats;
    }

    public void setBalonsRecuperats(int balonsRecuperats) {
        this.balonsRecuperats = balonsRecuperats;
    }

    @Override
    public String toString() {
        return super.toString() + "\nBalons Recuperats: " + balonsRecuperats + "\nGols: " + gols;
    }

    public Davanter altaDavanter() {
        Scanner kb = new Scanner(System.in);
        altaJugador();
        System.out.print("Escriu els gols anotats: ");
        setGols(kb.nextInt());

        System.out.print("Escriu els balons recuperats: ");
        setBalonsRecuperats(kb.nextInt());
        calcularSouIncentivat(); 
        return this;
    }

    @Override
    public void modificar() {
        Scanner kb = new Scanner(System.in);
        super.modificar();

        System.out.print("Gols del jugador: ");
        int gols = kb.nextInt();
        setGols(gols);

        System.out.print("Balons recuperats: ");
        int balonsRec = kb.nextInt();
        setBalonsRecuperats(balonsRec);
        
        calcularSouIncentivat();
    }
    
}
