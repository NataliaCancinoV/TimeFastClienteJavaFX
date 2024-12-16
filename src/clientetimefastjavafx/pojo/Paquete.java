package clientetimefastjavafx.pojo;

public class Paquete {
    private Integer idPaquete;
    private Integer noPaquete;
    private String descripcion;
    private float peso;
    private float alto;
    private float ancho;
    private float profundidad;
    private Integer envio;

    public Paquete() {
    }

    public Paquete(Integer idPaquete, Integer noPaquete, String descripcion, float peso, float alto, float ancho, float profundidad, Integer envio) {
        this.idPaquete = idPaquete;
        this.noPaquete = noPaquete;
        this.descripcion = descripcion;
        this.peso = peso;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.envio = envio;
    }

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public Integer getNoPaquete() {
        return noPaquete;
    }

    public void setNoPaquete(Integer noPaquete) {
        this.noPaquete = noPaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }

    public float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(float profundidad) {
        this.profundidad = profundidad;
    }

    public Integer getEnvio() {
        return envio;
    }

    public void setEnvio(Integer envio) {
        this.envio = envio;
    }
    
    
}
