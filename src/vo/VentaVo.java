package vo;

import java.time.LocalDate;
import java.util.List;

public class VentaVo {
    private int id;
    private ClienteVo cliente;
    private LocalDate fecha;
    List<EntradaVo> entradas;
    private double total;

    public VentaVo(int id, ClienteVo cliente, LocalDate fecha, List<EntradaVo> entradas, double total) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.entradas = entradas;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClienteVo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVo cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<EntradaVo> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<EntradaVo> entradas) {
        this.entradas = entradas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "VentaVo [id=" + id + ", cliente=" + cliente + ", fecha=" + fecha + ", entradas=" + entradas + ", total="
                + total + "]";
    }
    
}
