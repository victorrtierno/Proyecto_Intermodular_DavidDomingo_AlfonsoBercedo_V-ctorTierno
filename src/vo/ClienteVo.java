package vo;

public class ClienteVo extends PersonaVo {
    private String id;
    private String nombre;
    private String prApellido;
    private String sgApellido;
    private String email;
    
    private String telefono;

    public ClienteVo(String id, String nombre, String prApellido, String sgApellido, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.prApellido = prApellido;
        this.sgApellido = sgApellido;
        this.email = email;
        this.telefono = telefono;
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

    public String getPrApellido() {
        return prApellido;
    }

    public void setPrApellido(String prApellido) {
        this.prApellido = prApellido;
    }

    public String getSgApellido() {
        return sgApellido;
    }

    public void setSgApellido(String sgApellido) {
        this.sgApellido = sgApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "ClienteVo [id=" + id + ", nombre=" + nombre + ", prApellido=" + prApellido + ", sgApellido="
                + sgApellido + ", email=" + email + ", telefono=" + telefono + "]";
    }

}