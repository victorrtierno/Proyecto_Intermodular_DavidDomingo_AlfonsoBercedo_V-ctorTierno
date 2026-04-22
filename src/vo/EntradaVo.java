package vo;

public class EntradaVo {

    private int id;
    private AsientoVo asiento;
    private double precio;

    public EntradaVo(int id, AsientoVo asiento, double precio) {
        this.id = id;
        this.asiento = asiento;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AsientoVo getAsiento() {
        return asiento;
    }

    public void setAsiento(AsientoVo asiento) {
        this.asiento = asiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "EntradaVo [id=" + id + ", asiento=" + asiento + ", precio=" + precio + "]";
    }
}
