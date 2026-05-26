package ContactModule;

public class Contact implements Comparable <Contact> {
    private final String nombre;
    private final String numero;
    private final String mail;

    public Contact(String nombre, String numero, String mail) {
        this.nombre = nombre;
        this.numero = numero;
        this.mail = mail;
    }

    public String getNombre(){ return nombre;}
    public String getNumero(){ return numero;}
    public String getMail(){ return mail;}

    @Override
    public String toString() {
        return String.format(
                "Nombre: %s\n  Numero: %s\n  Mail: %s",
                nombre,
                numero,
                mail
        );
    }

    @Override
    public int compareTo(Contact otro) {
        return Long.compare(Long.parseLong(this.numero), Long.parseLong(otro.numero));
    }
}
