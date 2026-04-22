package vo;

public class AsientoVo {
    private int id;
    private int fila;
    private int numero;
    private String estado;
    
    public AsientoVo(int id, int fila, int numero, String estado) {
        this.id = id;
        this.fila = fila;
        this.numero = numero;
        this.estado = estado;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {  
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "AsientoVo [id=" + id + ", fila=" + fila + ", numero=" + numero + ", estado=" + estado + "]";
    }

}
