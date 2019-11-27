package micro.servicioproductos.entidades;

import javax.persistence.Entity;

@Entity
public class ProductoOrdenado {

    private Float cantidad;
    private String plan;
    private Float monto;
    private Orden orden;

    public ProductoOrdenado(Float cantidad, String plan, Float monto, Orden orden){

        this.cantidad = cantidad;
        this.plan = plan;
        this.monto = monto;
        this.orden = orden;
    }
    public ProductoOrdenado{
    }

    public Float getTotal() {
        return this.cantidad * this.monto;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
}
