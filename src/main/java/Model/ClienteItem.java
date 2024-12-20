package Model;

public class ClienteItem {

    private int id;
    private String nombre;

    public ClienteItem(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre; // Esto es lo que se mostrará en el ComboBox
    }
}
