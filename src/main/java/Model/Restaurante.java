package Model;

public class Restaurante {

    private int idRestaurante;
    private String nombre;
    private String tipoCocina;
    private int capacidad;
    private int IDHotel;

    public Restaurante(int idRestaurante, String nombre, String tipoCocina, int capacidad, int IDHotel) {
        this.idRestaurante = idRestaurante;
        this.nombre = nombre;
        this.tipoCocina = tipoCocina;
        this.capacidad = capacidad;
        this.IDHotel = IDHotel;
    }

    public int getIDHotel() {
        return IDHotel;
    }

    public void setIDHotel(int IDHotel) {
        this.IDHotel = IDHotel;
    }

    public int getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
