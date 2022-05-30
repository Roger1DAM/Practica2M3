package persones.plantilla.tecnics;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Scanner;

import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public class PreparadorFisic extends Tecnic implements Serializable{
    private static int incentiuAE = 100;

    @Override
    public void calcularSouIncentivat() {
        setSouIncentivat(getSouBase() + (getAnysExp() * incentiuAE));

    }

    public PreparadorFisic(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, double souBase, int anysExp) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email, nss, souBase, anysExp);
        calcularSouIncentivat();
    }

    public PreparadorFisic() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public PreparadorFisic altaPreparadorFisic() {
        Scanner kb = new Scanner(System.in);
        altaTecnic();
        calcularSouIncentivat();
        return this;
    }

    public void modificar() {
        super.modificar();
        calcularSouIncentivat();
    }

    
}
