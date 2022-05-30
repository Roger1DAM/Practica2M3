package persones.plantilla;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

import persones.Persona;
import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public abstract class Plantilla extends Persona implements Comparable<Plantilla>, Serializable {
    private static int numsEmpleats;
    private int numEmpleat;
    private String nss;
    private double souBase;
    private double souIncentivat;

    public static ArrayList<Plantilla> ordenarPerRol(HashMap<String, Plantilla> p) {
        ArrayList<Plantilla> al = new ArrayList<>();
        Iterator<Entry<String, Plantilla>> iterador = p.entrySet().iterator();
            while (iterador.hasNext()) {
                Entry<String, Plantilla> entry = iterador.next();
                al.add(entry.getValue());
            }
        Collections.sort(al);
        
        return al;
    }

    public static ArrayList<Plantilla> ordenarPerSouIncentivat(HashMap<String, Plantilla> p) {
        ArrayList<Plantilla> al = new ArrayList<>();
        Iterator<Entry<String, Plantilla>> iterador = p.entrySet().iterator();
            while (iterador.hasNext()) {
                Entry<String, Plantilla> entry = iterador.next();
                al.add(entry.getValue());
            }
        al.sort(new Comparator<Plantilla>() {
            @Override
            public int compare(Plantilla p1, Plantilla p2) {
                return (int) (p1.getSouIncentivat() - p2.getSouIncentivat());
            }
        });
        
        return al;
    }

    public static int getNumsEmpleats() {
        return numsEmpleats;
    }

    public static void setNumsEmpleats(int numsEmpleats) {
        Plantilla.numsEmpleats = numsEmpleats;
    }

    @Override
    public int compareTo(Plantilla a) {
        return this.getClass().getSimpleName().compareToIgnoreCase(a.getClass().getSimpleName());
    }

    public int getNumEmpleat() {
        return numEmpleat;
    }

    public void setNumEmpleat(int numEmpleat) {
        this.numEmpleat = numEmpleat;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public double getSouBase() {
        return souBase;
    }

    public void setSouBase(double souBase) {
        this.souBase = souBase;
    }
    
    public double getSouIncentivat() {
        return souIncentivat;
    }

    public void setSouIncentivat(double souIncentivat) {
        this.souIncentivat = souIncentivat;
    }
    public Plantilla() {

    }

    public Plantilla(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon,
            Correu email, String nss, double souBase) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email);
        this.numEmpleat = ++numsEmpleats;
        this.nss = nss;
        this.souBase = souBase;
        calcularSouIncentivat();
    }

    @Override
    public String toString() {
        return super.toString() + "\nNSS: " + getNss() + "\nNumEmpleat: " + getNumEmpleat() + "\nSou Base: " + getSouBase() + "\nSou Incentivat: " + getSouIncentivat();
    }

    public abstract void calcularSouIncentivat();

    public void altaTreballador() {
        Scanner kb = new Scanner(System.in);
        altaPersona();

        System.out.print("Escriu el NSS: ");
        setNss(kb.next());

        System.out.print("Escriu el sou base: ");
        setSouBase(kb.nextInt());

        setNumEmpleat(++numsEmpleats);
    }

    @Override
    public void modificar() {
        Scanner kb = new Scanner(System.in);
        super.modificar();

        System.out.print("Escriu el nou SOU: ");
        double sou = kb.nextDouble();
        setSouBase(sou);
    }
}
