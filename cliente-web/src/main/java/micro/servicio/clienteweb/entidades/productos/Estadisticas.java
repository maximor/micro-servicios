package micro.servicio.clienteweb.entidades.productos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Estadisticas {
    private int comprasRealizadas;
    private int comprasPendientes;
    private int comprasPorDia;

    public Estadisticas() {
    }

    public Estadisticas(int comprasRealizadas, int comprasPendientes, int comprasPorDia) {
        this.comprasRealizadas = comprasRealizadas;
        this.comprasPendientes = comprasPendientes;
        this.comprasPorDia = comprasPorDia;
    }

    public int getComprasRealizadas() {
        return comprasRealizadas;
    }

    public void setComprasRealizadas(int comprasRealizadas) {
        this.comprasRealizadas = comprasRealizadas;
    }

    public int getComprasPendientes() {
        return comprasPendientes;
    }

    public void setComprasPendientes(int comprasPendientes) {
        this.comprasPendientes = comprasPendientes;
    }

    public int getComprasPorDia() {
        return comprasPorDia;
    }

    public void setComprasPorDia(int comprasPorDia) {
        this.comprasPorDia = comprasPorDia;
    }
}
