package persones;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;

public class Soci extends Persona implements Comparable<Soci>, Comparator<Soci>, Serializable{
    private static int numLocalitat = 499;
    private static int numSocis;
    private int numSoci;
    private int localitat;
    private double quota;

    public static int getNumLocalitat() {
        return numLocalitat;
    }

    public static void setNumLocalitat(int numLocalitat) {
        Soci.numLocalitat = numLocalitat;
    }

    public static int getNumSocis() {
        return numSocis;
    }

    public static void setNumSocis(int numSocis) {
        Soci.numSocis = numSocis;
    }

    public void setQuota(double quota) {
        this.quota = quota;
    }

    public Scanner getKb() {
        return kb;
    }

    private transient final Scanner kb = new Scanner(System.in);

    public ArrayList<Soci> ordenarPerCognom(HashMap<String, Soci> socis) {
        ArrayList<Soci> al = new ArrayList<>();
        Iterator<Entry<String, Soci>> iterador = socis.entrySet().iterator();
            while (iterador.hasNext()) {
                Entry<String, Soci> entry = iterador.next();
                al.add(entry.getValue());
            }
            Collections.sort(al);      
            
        return al;

    }

    public ArrayList<Soci> ordenarPerLocalitat(HashMap<String, Soci> socis) {
        ArrayList<Soci> al = new ArrayList<>();
        Iterator<Entry<String, Soci>> iterador = socis.entrySet().iterator();
            while (iterador.hasNext()) {
                Entry<String, Soci> entry = iterador.next();
                al.add(entry.getValue());
            }
        al.sort(new Comparator<Soci>() {
            @Override
            public int compare(Soci s1, Soci s2) {
                return (int) s1.getLocalitat() - s2.getLocalitat();
            }
        });
        
        return al;
    }

    public ArrayList<Soci> ordenarPerQuota(HashMap<String, Soci> socis) {
        ArrayList<Soci> al = new ArrayList<>();
        Iterator<Entry<String, Soci>> iterador = socis.entrySet().iterator();
            while (iterador.hasNext()) {
                Entry<String, Soci> entry = iterador.next();
                al.add(entry.getValue());
            }
        al.sort(new Comparator<Soci>() {
            @Override
            public int compare(Soci s1, Soci s2) {
                return (int)(s1.getQuota() - s2.getQuota());
            }
        });
        
        return al;
    }

    public Soci altaSoci() {
        Dni dni = new Dni();
        String dniStr;

        do {
            System.out.print("Escriu el dni del soci: ");
            dniStr = kb.nextLine();
        } while (!dni.validarDNI(dniStr));

        dni.setDni(dniStr);
        setDni(dni);

        System.out.print("Escriu el nom: ");
        String nom = kb.nextLine();
        System.out.print("Escriu el primer cognom: ");
        String cognom1 = kb.nextLine();
        System.out.print("Escriu el segon cognom: ");
        String cognom2 = kb.nextLine();

        boolean dataCorrecta;
        LocalDate data_naix = null;
        do {
            dataCorrecta = true;
            System.out.print("Escriu la data de naixement (YYYY-MM-DD): ");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
            try {
                data_naix = (LocalDate.parse(kb.next(), dateFormat));
            } catch (Exception e) {
                dataCorrecta = false;
            }
        } while (!dataCorrecta);

        Telefon telefon = new Telefon();
        String telefonStr;

        do {
            System.out.print("Escriu el número de telèfon: ");
            kb.nextLine();
            telefonStr = (kb.next());
        } while (!telefon.validarTel(telefonStr));

        telefon.setTelf(telefonStr);
        setTelefon(telefon);

        Correu email = new Correu();
        String emailStr;
        do {
            System.out.print("Escriu el correu electrònic: ");
            kb.nextLine();
            emailStr = (kb.next());
        } while (!email.validarCorreu(emailStr));

        email.setCorreu(emailStr);
        setEmail(email);

        System.out.print("Escriu la quota del soci: ");
        int quota = kb.nextInt();
        
        Soci s = new Soci(dni, nom, cognom1, cognom2, data_naix, telefon, email, quota);
        return s;
    }

    public Soci modificar(Soci soci) {        
        System.out.println("Modificant al soci: " + soci.getNom() + " " + soci.getCognom1());
        System.out.println("Deixa en blanc els camps que no vulguis modificar.");
        super.modificar();

        System.out.print("Escriu la nova QUOTA: ");
        double quota = kb.nextInt();
        if (quota != 0) {
            setQuota(quota);
        }

        return soci;
    }

    public int getNumSoci() {
        return numSoci;
    }

    public void setNumSoci(int numSoci) {
        this.numSoci = numSoci;
    }

    public int getLocalitat() {
        return localitat;
    }

    public void setLocalitat(int localitat) {
        this.localitat = localitat;
    }

    public double getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public Soci(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon, Correu email, double quota) {
        super(dni, nom, cognom1, cognom2, data_naix, telefon, email);
        this.quota = quota;
        this.localitat = ++numLocalitat;
        this.numSoci = ++numSocis;
    }

    public Soci() {

    }

    @Override
    public String toString() {
        return super.toString() + "\nQuota: " + getQuota() + "\nNº soci: " + getNumSoci() + "\nLocalitat: " + getLocalitat(); 
    }

    @Override
    public int compareTo(Soci s) {
        return this.getCognom1().compareToIgnoreCase(s.getCognom1());
    }

    @Override
    public int compare(Soci o1, Soci o2) {
        // TODO Auto-generated method stub
        return 0;
    }
    
  
    
}
