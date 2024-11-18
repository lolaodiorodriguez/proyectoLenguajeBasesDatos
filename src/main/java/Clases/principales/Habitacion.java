
package Clases.principales;


public class Habitacion {
    
    private int idHabitacion;
    private String tipoHabitacion;
    private double precio;
    private int capacidad;
    private int disponibilidad;

    public Habitacion() {
    }

    public Habitacion(int idHabitacion, String tipoHabitacion, double precio, int capacidad, int disponibilidad) {
        this.idHabitacion = idHabitacion;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
        this.capacidad = capacidad;
        this.disponibilidad = disponibilidad;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}
