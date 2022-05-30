import java.io.IOException;

public class ClubFutbolApp {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Club c = new Club("Tarrega C.F", "123456789", "C/ Tal de pasqual", "121212121", "tarregaCF@gmail.com", "www.tarregaCF.com");
        c.gestionarClub();
    }
}
