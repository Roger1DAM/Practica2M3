package persones.plantilla.tecnics;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import persones.plantilla.Plantilla;
import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public abstract class Tecnic extends Plantilla implements Serializable{
    private int anysExp;

    public int getAnysExp() {
        return anysExp;
    }

    public void setAnysExp(int anysExp) {
        this.anysExp = anysExp;
    }

    public Tecnic(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, double souBase, int anysExp) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email, nss, souBase);
        this.anysExp = anysExp;
    }
    
    public Tecnic() {

    }

    @Override
    public String toString() {
        return super.toString() + "\nAnys Exp: " + anysExp;
    }

    public void altaTecnic() {
        Scanner kb = new Scanner(System.in);
        altaTreballador();
        System.out.print("Escriu els anys d'experiència: ");
        setAnysExp(kb.nextInt());
    }

    

    
}
