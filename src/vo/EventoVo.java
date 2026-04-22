package vo;

import java.time.LocalDate;

public class EventoVo {
    private String id;
    private String nombre;
    private LocalDate fecha;
    private String lugar;
    private String estado;
    public EventoVo(String id, String nombre, LocalDate fecha, String lugar, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.estado = estado;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public String getLugar() {
        return lugar;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "EventoVo [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", lugar=" + lugar + ", estado="
                + estado + "]";
    }
}