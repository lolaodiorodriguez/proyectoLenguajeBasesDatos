package Clases.principales;

import java.util.Date;

public class Reserva {
    private int idReserva;
    private Clientes cliente;  
    private Hotel hotel;  
    private Date fechaInicio;
    private Date fechaFin;
    private String estadoReserva;
    private Habitacion habitacion;  

    
    public Reserva(int idReserva, Clientes cliente, Hotel hotel, Date fechaInicio, Date fechaFin, String estadoReserva, Habitacion habitacion) {
        this.idReserva = idReserva;
        this.cliente = cliente;
        this.hotel = hotel;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoReserva = estadoReserva;
        this.habitacion = habitacion;
    }

    
    public Clientes getCliente() { return cliente; }
    public Hotel getHotel() { return hotel; }
    public Habitacion getHabitacion() { return habitacion; }
    public Date getFechaInicio() { return fechaInicio; }
    public Date getFechaFin() { return fechaFin; }
    public String getEstadoReserva() { return estadoReserva; }
    public int getIdReserva() { return idReserva; }
}