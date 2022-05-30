package persones.plantilla.tecnics;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public class Entrenador extends Tecnic implements Serializable{
    private int numTrofeus;
    private static int incentiuTrofeus = 1000;

    @Override
    public void calcularSouIncentivat() {
        setSouIncentivat(getSouBase() + (getNumTrofeus() * incentiuTrofeus));
    }

    public int getNumTrofeus() {
        return numTrofeus;
    }

    public void setNumTrofeus(int numTrofeus) {
        this.numTrofeus = numTrofeus;
    }

    public Entrenador(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, double souBase, int anysExp, int numTrofeus) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email, nss, souBase, anysExp);
        this.numTrofeus = numTrofeus;
        calcularSouIncentivat();

    }

    public Entrenador() {

    }

    @Override
    public String toString() {
        return super.toString() + "\nNum. Trofeus: " + numTrofeus;
    }

    public Entrenador altaEntrenador() {
        Scanner kb = new Scanner(System.in);
        altaTecnic();
        System.out.print("Escriu el número de trofeus: ");
        setNumTrofeus(kb.nextInt());
        calcularSouIncentivat();
        return this;
    }

    public void modificar() {
        Scanner kb = new Scanner(System.in);
        super.modificar();

        System.out.print("Escriu el número de trofeus: ");
        int numTrofeus = kb.nextInt();
        setNumTrofeus(numTrofeus);
        calcularSouIncentivat();
    }

    
}
