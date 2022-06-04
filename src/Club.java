import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Map.Entry;

import persones.Soci;
import persones.plantilla.Plantilla;
import persones.plantilla.jugadors.Davanter;
import persones.plantilla.jugadors.Defensa;
import persones.plantilla.jugadors.Jugador;
import persones.plantilla.jugadors.Migcampista;
import persones.plantilla.jugadors.Porter;
import persones.plantilla.tecnics.Entrenador;
import persones.plantilla.tecnics.PreparadorFisic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class Club implements Serializable{
    private String nom;
    private String CIF;
    private String adreça;
    private String telefon;
    private String email;
    private String web;
    private HashMap<String, Plantilla> plantilla = new HashMap<>();
    private HashMap<String, Soci> socis = new HashMap<>();

    public void gestionarClub() throws IOException, ClassNotFoundException {     
        File file = new File("src\\files\\DadesClub.dat");
        if (file.isFile()) {
            llegirDadesFitxer();
        }

        boolean sortir = false;

        do {
            System.out.println("\n**********GESTIONAR CLUB**********");
            System.out.println("1. Consultar dades del club");
            System.out.println("2. Gestionar socis");
            System.out.println("3. Gestionar la plantilla");
            System.out.println("4. Visualitzar dades econòmiques del club");
            System.out.println("5. Sortir");

            int opcio = comprovarInputValid();

            switch (opcio) {
                case 1:
                    consultarDadesClub();
                    break;
                case 2:
                    gestionarSocis();
                    break;
                case 3:
                    gestionarPlantilla();
                    break;
                case 4:
                    visualitzarDadesEconomiques();
                    break;
                case 5:
                    persistirDadesClub();
                    sortir = true;
                    break;
                default:
                    System.out.println("Valor no vàlid");
            }
        
        } while (!sortir);
    }

    private void visualitzarDadesEconomiques() {
        System.out.println("\n· · · · INGRESSOS DEL CLUB · · · ·");
        double ingressos = 0;
        ArrayList<Soci> socisArray = new ArrayList<>();
        Iterator<Entry<String, Soci>> iterador = socis.entrySet().iterator();
            while (iterador.hasNext()) {
                Entry<String, Soci> entry = iterador.next();
                socisArray.add(entry.getValue());
        }
        for (Soci s : socisArray) {
            ingressos += s.getQuota();
        }
        System.out.println("Quotes de socis: " + ingressos + " euros");

        System.out.println("\n · · · · DESPESES DEL CLUB · · · ·");
        double despeses = 0;
        ArrayList<Plantilla> plantillaArray = new ArrayList<>();
        Iterator<Entry<String, Plantilla>> iterador2 = plantilla.entrySet().iterator();
            while (iterador2.hasNext()) {
                Entry<String, Plantilla> entry = iterador2.next();
                plantillaArray.add(entry.getValue());
        }
        for (Plantilla p : plantillaArray) {
            despeses += p.getSouIncentivat();
        }
        System.out.println("Salari dels treballadors: " + despeses + " euros");
    }

    private void consultarDadesClub() {
        
        System.out.println("\nNom: " + this.nom);
        System.out.println("CIF: " + this.CIF);
        System.out.println("Adreça: " + this.adreça);
        System.out.println("Telèfon: " + this.telefon);
        System.out.println("Email: " + this.email);
        System.out.println("Web: " + this.web);
    }

    public void gestionarSocis() {
        Soci s = new Soci();
        Scanner kb = new Scanner(System.in);
        boolean sortir = false;

        do {
            System.out.println("**********GESTIONAR SOCIS**********");
            System.out.println("1. Visualitzar tots els socis");
            System.out.println("2. Donar d'alta un soci");
            System.out.println("3. Modificar un soci");
            System.out.println("4. Donar de baixa un soci");
            System.out.println("5. Sortir");

            int opcio = comprovarInputValid();       

            switch (opcio) {
                case 1:
                    visualitzarSocis();
                    break;
                case 2:
                    s = s.altaSoci();
                    String dniStr = s.getDni().getDni();
                    socis.put(dniStr, s);
                    System.out.println("Soci donat d'alta correctament!\n");
                    break;
                case 3:
                    dniStr = null;
                    System.out.print("Escriu el dni del soci que vols modificar: ");
                    try {
                        dniStr = kb.nextLine();
                        s = socis.get(dniStr);
                        s = s.modificar(s);
                    } catch (Exception e) {
                        System.out.println("No s'ha trobat el soci.");
                    }
                    socis.put(dniStr, s);
                    break;
                case 4:
                    try {
                        System.out.print("Escriu el dni del soci que vols donar de baixa: ");
                        dniStr = kb.nextLine();
                        if (socis.containsKey(dniStr)) {
                            socis.remove(dniStr);
                            System.out.println("Soci donat de baixa correctament!\n");
                        } else {
                            System.out.println("No s'ha trobat aquest DNI.");
                        }
                    } catch (Exception e) {
                        System.out.println("No s'ha trobat el soci.");
                    }
                    break;
                case 5:
                    sortir = true;
                    break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }

    private void visualitzarSocis() {
        ArrayList<Soci> al = new ArrayList<>();
        Scanner kb = new Scanner(System.in);
        boolean sortir = false;

        Soci s = new Soci();
        do {
            System.out.println("**********VISUALITZAR SOCIS**********");
            System.out.println("1. Ordenar per cognom");
            System.out.println("2. Ordenar per localitat");
            System.out.println("3. Ordenar per quota");
            System.out.println("4. Sortir");

            int opcio = comprovarInputValid();
            
            switch (opcio) {
                case 1:
                    al = s.ordenarPerCognom(socis);
                    for (Soci soci : al) {
                        System.out.println("-------------------------");
                        System.out.println(soci);
                    }
                    break;
                case 2:
                    al = s.ordenarPerLocalitat(socis);
                    for (Soci soci : al) {
                        System.out.println("-------------------------");
                        System.out.println(soci); 
                    }
                    break;
                case 3:
                    al = s.ordenarPerQuota(socis);
                    for (Soci soci : al) {
                        System.out.println("-------------------------");
                        System.out.println(soci);   
                    }
                    break;
                case 4:
                    sortir = true;
                    break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }

    public Club(String nom, String cIF, String adreça, String telefon, String email, String web) {
        this.nom = nom;
        CIF = cIF;
        this.adreça = adreça;
        this.telefon = telefon;
        this.email = email;
        this.web = web;
    }

    public Club() {

    }

    public void gestionarPlantilla() {
        Scanner kb = new Scanner(System.in);
        boolean sortir = false;
        String dniStr;

        do {
            System.out.println("**********GESTIONAR PLANTILLA**********");
            System.out.println("1. Visualitzar la plantilla");
            System.out.println("2. Donar d'alta un treballador");
            System.out.println("3. Modificar un treballador");
            System.out.println("4. Donar de baixa un treballador");
            System.out.println("5. Sortir");

            int opcio = comprovarInputValid();

            switch (opcio) {
                case 1:
                    visualitzarPlantilla();
                    break;
                case 2:       
                    altaTreballador();
                    break;
                case 3:
                    modificarTreballador();
                    break;
                case 4:
                    try {
                        System.out.print("Escriu el dni del treballador que vols donar de baixa: ");
                        dniStr = kb.nextLine();
                        if (plantilla.containsKey(dniStr)) {
                            plantilla.remove(dniStr);
                            System.out.println("Treballador donat de baixa correctament!\n");
                        } else {
                            System.out.println("No s'ha trobat aquest DNI.");
                        }                       
                    } catch (Exception e) {
                        System.out.println("No s'ha trobat el treballador.");
                    }
                    break; 
                case 5:
                    sortir = true;
                    break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }

    private void modificarTreballador() {
        Scanner kb = new Scanner(System.in);
        String dniStr;
        System.out.print("Escriu el dni del treballador que vols modificar: ");

        try {
            dniStr = kb.nextLine();
            plantilla.get(dniStr).modificar();
            System.out.println("Treballador modificat correctament!");
        } catch (Exception e) {
            System.out.println("No s'ha trobat el treballador.");
        }
    }

    private void altaTreballador() { 
        Scanner kb = new Scanner(System.in);
        boolean sortir = false;

        do {
            System.out.println("\nQuin treballador vols donar d'alta?");
            System.out.println("1. Entrenador");
            System.out.println("2. Preparador físic");
            System.out.println("3. Davanter");
            System.out.println("4. Defensa");
            System.out.println("5. Migcampista");
            System.out.println("6. Porter");
            System.out.println("7. Sortir");

            int opcio = comprovarInputValid();

            switch (opcio) {
                case 1:
                    Entrenador e = new Entrenador();
                    e = e.altaEntrenador();
                    plantilla.put(e.getDni().getDni(), e);
                    System.out.println("Entrenador donat d'alta correctament!");
                    break;
                case 2:
                    PreparadorFisic p = new PreparadorFisic();
                    p = p.altaPreparadorFisic();
                    plantilla.put(p.getDni().getDni(), p);
                    System.out.println("Preparador fisic donat d'alta correctament!");
                    break;
                case 3:
                    Davanter d = new Davanter();
                    d = d.altaDavanter();
                    plantilla.put(d.getDni().getDni(), d);
                    System.out.println("Davanter donat d'alta correctament!");           
                    break;
                case 4:
                    Defensa def = new Defensa();
                    def = def.altaDefensa();
                    plantilla.put(def.getDni().getDni(), def);
                    System.out.println("Defensa donat d'alta correctament!");
                    break;
                case 5:
                    Migcampista m = new Migcampista();
                    m = m.altaMigcampista();
                    plantilla.put(m.getDni().getDni(), m);
                    System.out.println("Migcampista donat d'alta correctament!");
                    break;
                case 6:
                    Porter po = new Porter();
                    po = po.altaPorter();
                    plantilla.put(po.getDni().getDni(), po);
                    System.out.println("Porter donat d'alta correctament!");
                    break;
                case 7:
                    sortir = true;
                    break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }

    private void visualitzarPlantilla() {
        ArrayList<Plantilla> al = new ArrayList<>();
        Scanner kb = new Scanner(System.in);
        boolean sortir = false;

        do {
            System.out.println("**********VISUALITZAR PLANTILLA**********");
            System.out.println("1. Ordenar per rol");
            System.out.println("2. Ordenar per sou");
            System.out.println("3. Sortir");

            int opcio = comprovarInputValid();
            
            switch (opcio) {
                case 1:
                    al = Plantilla.ordenarPerRol(plantilla);
                    for (Plantilla p : al) {
                        System.out.println("----------------------");
                        System.out.println(p);
                    }
                    break;
                case 2:
                    al = Plantilla.ordenarPerSouIncentivat(plantilla);
                    for (Plantilla p : al) {
                        System.out.println("----------------------");
                        System.out.println(p);
                    }                    
                    break;
                case 3:
                    sortir = true;
                    break;
                default:
                    System.out.println("Valor no vàlid");
            }
        } while (!sortir);
    }

    private int comprovarInputValid() {
        Scanner kb = new Scanner(System.in);
        int opcio = 0;
        boolean valid = false;

        while (!valid) {
            try{
                opcio = Integer.parseInt(kb.nextLine());
                valid = true;
            }catch (NumberFormatException ex) {
                System.out.println("Introdueix un valor numèric");
            }
        }
        return opcio;
    }

        private void persistirDadesClub() throws IOException {
        File file = new File("src\\files\\DadesClub.dat");
        FileOutputStream fileOutput = new FileOutputStream(file);
        ObjectOutputStream serialitzador = new ObjectOutputStream(fileOutput);

        serialitzador.writeObject(this);
        serialitzador.writeObject(Soci.getNumLocalitat());
        serialitzador.writeObject(Soci.getNumSocis());
        serialitzador.writeObject(Plantilla.getNumsEmpleats());
        serialitzador.writeObject(Jugador.getDorsals());
    }

    private void llegirDadesFitxer() throws IOException, ClassNotFoundException {
        File file = new File("src\\files\\DadesClub.dat");
        if (file.isFile()) {
            FileInputStream fileInput = new FileInputStream(file);
            ObjectInputStream deserialitzador = new ObjectInputStream(fileInput);

            Club club = new Club();
            club = ((Club) deserialitzador.readObject());
            this.setNom(club.getNom());
            this.setCIF(club.getCIF());
            this.setAdreça(club.getAdreça());
            this.setTelefon(club.getTelefon());
            this.setEmail(club.getEmail());
            this.setWeb(club.getWeb());
            this.setPlantilla(club.getPlantilla());
            this.setSocis(club.getSocis());

            Soci.setNumLocalitat(((int)deserialitzador.readObject()));
            Soci.setNumSocis(((int)deserialitzador.readObject()));
            Plantilla.setNumsEmpleats(((int)deserialitzador.readObject()));
            Jugador.setDorsals(((int)deserialitzador.readObject()));
            
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String cIF) {
        CIF = cIF;
    }

    public String getAdreça() {
        return adreça;
    }

    public void setAdreça(String adreça) {
        this.adreça = adreça;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public HashMap<String, Plantilla> getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(HashMap<String, Plantilla> plantilla) {
        this.plantilla = plantilla;
    }

    public HashMap<String, Soci> getSocis() {
        return socis;
    }

    public void setSocis(HashMap<String, Soci> socis) {
        this.socis = socis;
    }
}
