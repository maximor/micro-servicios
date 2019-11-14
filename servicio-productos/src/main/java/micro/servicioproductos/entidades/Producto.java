package micro.servicioproductos.entidades;

public class Producto {

    private String id_Producto="";
    private String Plan = "";
    private float Monto = 0;


    public String getId() {
        return id_Producto;
    }

    public void setId(String id) {
        this.id_Producto = id;
    }

    public String getPlan() {
        return Plan;
    }

    public void setPlan(String plan) {
        this.Plan = plan;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float monto) {
        this.Monto = monto;
    }

}
