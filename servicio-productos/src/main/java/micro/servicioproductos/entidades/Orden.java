package micro.servicioproductos.entidades;

import java.time.LocalDate;

public class Orden {


    private String Id_Orden;
    private Long Id_User;
    private String Plan;
    private float Monto;
    private LocalDate Fecha;



    public Orden(String Id_Orden, String Plan, float Monto, LocalDate Fecha, Long Id_User) {
        this.Id_Orden = Id_Orden;
        this.Plan = Plan;
        this.Monto = Monto;
        this.Fecha = Fecha;
        this.Id_User = Id_User;
    }

    public Orden()
    {

    }

    public String getId_Orden() {
        return Id_Orden;
    }

    public void setId_Orden(String Id_Orden) {
        this.Id_Orden = Id_Orden;
    }

    public String getPlan() {
        return Plan;
    }

    public void setPlan(String Plan) {
        this.Plan = Plan;
    }

    public float getMonto() {
        return Monto;
    }

    public void setMonto(float Monto) {
        this.Monto = Monto;
    }

    public LocalDate getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDate Fecha) {
        this.Fecha = Fecha;
    }

    public Long getId_User() {
        return Id_User;
    }

    public void setId_User(Long Id_User) {
        this.Id_User = Id_User;
    }

}
