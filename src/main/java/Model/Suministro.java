package Model;

public class Suministro {

    private int idSuministro;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private double precioUnitario;
    private int idProveedor;
    private int idHotel;

    public Suministro(int idSuministro, String nombre, String descripcion, int cantidad, double precioUnitario, int idProveedor, int idHotel) {
        this.idSuministro = idSuministro;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idProveedor = idProveedor;
        this.idHotel = idHotel;
    }

    public int getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(int idSuministro) {
        this.idSuministro = idSuministro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    @Override
    public String toString() {
        return "Suministro{"
                + "idSuministro=" + idSuministro
                + ", nombre='" + nombre + '\''
                + ", descripcion='" + descripcion + '\''
                + ", cantidad=" + cantidad
                + ", precioUnitario=" + precioUnitario
                + ", idProveedor=" + idProveedor
                + ", idHotel=" + idHotel
                + '}';
    }
}
