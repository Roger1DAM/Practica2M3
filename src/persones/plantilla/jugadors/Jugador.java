package persones.plantilla.jugadors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import persones.plantilla.Plantilla;
import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public abstract class Jugador extends Plantilla implements Serializable{
    private static int dorsals;
    private int dorsal;
    private boolean esTitular;

    public Jugador(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, int souBase, boolean esTitular) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email, nss, souBase);
        this.dorsal = ++dorsals;
        this.esTitular = esTitular;
    }

    public Jugador() {

    }

    @Override
    public String toString() {
        return super.toString() + "\nDorsal: " + dorsal + "\nÉs titular: " + esTitular;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public static int getDorsals() {
        return dorsals;
    }

    public boolean getEsTitular() {
        return esTitular;
    }

    public void setEsTitular(boolean esTitular) {
        this.esTitular = esTitular;
    }

    public static void setDorsals(int dorsals) {
        Jugador.dorsals = dorsals;
    }

    public void altaJugador() {
        Scanner kb = new Scanner(System.in);
        altaTreballador();
        boolean valid = false;

        do {
            System.out.print("El jugador és titular? (S/N)");
            char titular = Character.toUpperCase(kb.nextLine().charAt(0));

            if (titular == 'S') {
                setEsTitular(true);
                valid = true;
            } else if (titular == 'N') {
                setEsTitular(false);
                valid = true;
            } else {
                System.out.println("Valor no vàlid");
            }
        } while (!valid);
    }

    @Override
    public void modificar() {
        Scanner kb = new Scanner(System.in);
        super.modificar();

        boolean valid = false;

        do {
            System.out.print("El jugador és titular? (S/N)");
            char titular = Character.toUpperCase(kb.nextLine().charAt(0));

            if (titular == 'S') {
                setEsTitular(true);
                valid = true;
            } else if (titular == 'N') {
                setEsTitular(false);
                valid = true;
            } else {
                System.out.println("Valor no vàlid");
            }
        } while (!valid);

        calcularSouIncentivat();
    }

    
}
