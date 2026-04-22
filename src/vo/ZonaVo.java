package vo;

public class ZonaVo {

    private int id;
    private String nombre;
    private double precioBase;

    public ZonaVo(
            int id,
            String nombre,
            double precioBase
    ) {

        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;

    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {

        return id +
               " - " +
               nombre +
               " (" +
               precioBase +
               "€)";

    }

}