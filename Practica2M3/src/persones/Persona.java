package persones;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import validacions.Correu;
import validacions.Dni;
import validacions.Telefon;



public abstract class Persona implements Serializable {
    private Dni dni;
    private String nom;
    private String cognom1;
    private String cognom2;
    private LocalDate data_naix;
    private Telefon telefon;
    private Correu email;

    public Persona(Dni dni, String nom, String cognom1, String cognom2, LocalDate data_naix, Telefon telefon, Correu email) {
        this.dni = dni;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.data_naix = data_naix;
        this.telefon = telefon;
        this.email = email;
    }

    public Persona() {

    }

    public Dni getDni() {
        return dni;
    }

    public void setDni(Dni dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public LocalDate getData_naix() {
        return data_naix;
    }

    public void setData_naix(LocalDate data_naix) {
        this.data_naix = data_naix;
    }

    public Telefon getTelefon() {
        return telefon;
    }

    public void setTelefon(Telefon telefon) {
        this.telefon = telefon;
    }

    public Correu getEmail() {
        return email;
    }

    public void setEmail(Correu email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "\nDni: " + getDni() + "\nNom: " + getNom() + "\nCognoms: " + cognom1 + " " + cognom2 + "\nData naixement: " + data_naix + "\nEmail: " + email + "\nTelefon: " + telefon;
    }

    public void altaPersona() {
        Scanner kb = new Scanner(System.in);
        Dni dni = new Dni();
        String dniStr;

        do {
            System.out.print("Escriu el dni del soci: ");
            dniStr = kb.nextLine();
        } while (!dni.validarDNI(dniStr));

        dni.setDni(dniStr);
        setDni(dni);

        System.out.print("Escriu el nom: ");
        setNom(kb.nextLine());
        System.out.print("Escriu el primer cognom: ");
        setCognom1(kb.nextLine());;
        System.out.print("Escriu el segon cognom: ");
        setCognom2(kb.nextLine());

        boolean dataCorrecta;
        LocalDate data_naix = null;
        do {
            dataCorrecta = true;
            System.out.print("Escriu la data de naixement (YYYY-MM-DD): ");
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-M-d");
            try {
                setData_naix(LocalDate.parse(kb.next(), dateFormat));
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
    }

}


