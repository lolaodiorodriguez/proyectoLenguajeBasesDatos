package Model;

import java.sql.Date;

public class Pago {

    private int idpago;
    private int idreserva;
    private int monto;
    private Date fecha;
    private String metodo;
    private int idfactura;

    public Pago(int idpago, int idreserva, int monto, Date fecha, String metodo, int idfactura) {
        this.idpago = idpago;
        this.idreserva = idreserva;
        this.monto = monto;
        this.fecha = fecha;
        this.metodo = metodo;
        this.idfactura = idfactura;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

}
