package vo;

import java.time.LocalDate;

public class EventoVo {

    private int id;
    private String nombre;
    private LocalDate fecha;
    private String lugar;
    private String estado;

    public EventoVo(int id, String nombre, LocalDate fecha, String lugar, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.lugar = lugar;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "EventoVo [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha + ", lugar=" + lugar + ", estado="
                + estado + "]";
    }

    public Object getNombre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }

    public Object getFecha() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFecha'");
    }

    public Object getLugar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLugar'");
    }

    
}
