
package Clases.principales;


public class Hotel {
    
    private int idHotel;
    private String nombre;
    private String direccion;
    private String telefono;

    public Hotel() {
    }
    
    
    public Hotel(int idHotel, String nombre, String direccion, String telefono) {
        this.idHotel = idHotel;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
