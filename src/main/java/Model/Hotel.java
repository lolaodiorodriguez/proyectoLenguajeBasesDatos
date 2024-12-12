package Model;

public class Hotel {

    private int id_hotel;
    private String nombre;
    private String Direccion;
    private String telefono;

    public Hotel(int id_hotel, String nombre, String Direccion, String telefono) {
        this.id_hotel = id_hotel;
        this.nombre = nombre;
        this.Direccion = Direccion;
        this.telefono = telefono;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
