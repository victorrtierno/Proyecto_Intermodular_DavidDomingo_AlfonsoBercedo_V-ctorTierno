package vo;

import java.time.LocalDate;
import java.util.List;

public class VentaVo {
    private int id;
    private ClienteVo cliente;
    private LocalDate fecha;
    List<EntradaVo> entradas;
    private double total;
    
}
