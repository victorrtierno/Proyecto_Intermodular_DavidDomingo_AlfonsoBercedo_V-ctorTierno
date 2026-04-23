package vo;

import java.util.ArrayList;
import java.util.List;

public class CarritoVo {

    private List<AsientoVo> asientos;

    public CarritoVo() {
        this.asientos = new ArrayList<>();
    }

    public void add(AsientoVo asiento) {
        asientos.add(asiento);
    }

    public void remove(AsientoVo asiento) {
        asientos.remove(asiento);
    }

    public List<AsientoVo> getAsientos() {
        return asientos;
    }

<<<<<<< HEAD
    public double calcularTotal(double precioBase) {
        return asientos.size() * precioBase;
    }

    public void limpiar() {
        asientos.clear();
    }

}
=======
    @Override
    public String toString() {
        return "CarritoVo [asientos=" + asientos + "]";
    }
}
>>>>>>> david
