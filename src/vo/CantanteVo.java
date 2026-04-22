package vo;

public class CantanteVo {

    private int id;
    private String nombre;
    private String descripcion;

    public CantanteVo(
            int id,
            String nombre,
            String descripcion
    ) {

        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {

        return id + " - " + nombre;

    }

}