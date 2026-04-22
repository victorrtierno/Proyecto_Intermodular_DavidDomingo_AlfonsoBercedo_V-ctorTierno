package vo;

import java.util.ArrayList;
import java.util.List;

public class CarritoVo {

    private List<AsientoVo> asientos = new ArrayList<>();

    public void add(AsientoVo a) {
        asientos.add(a);
    }

    public List<AsientoVo> getAsientos() {
        return asientos;
    }
}
