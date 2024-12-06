
package Model;
import java.util.Date;

public class Factura {
    private int idFactura;
    private int idReserva;
    private Date fechaEmision;
    private double montoTotal;

   
    public Factura(int idFactura, int idReserva, Date fechaEmision, double montoTotal) {
        this.idFactura = idFactura;
        this.idReserva = idReserva;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
    }

   
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
