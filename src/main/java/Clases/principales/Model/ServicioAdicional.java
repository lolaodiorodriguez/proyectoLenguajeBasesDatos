package Vista;


import java.sql.Date;

public class ServicioAdicional {
    private int idServicio;
    private String descripcion;
    private Date fecha;
    private double precio;
    private int idReserva;

    public ServicioAdicional(int idServicio, String descripcion, Date fecha, double precio, int idReserva) {
        this.idServicio = idServicio;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.precio = precio;
        this.idReserva = idReserva;
    }

    public int getIdServicio() { return idServicio; }
    public void setIdServicio(int idServicio) { this.idServicio = idServicio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getIdReserva() { return idReserva; }
    public void setIdReserva(int idReserva) { this.idReserva = idReserva; }
}
