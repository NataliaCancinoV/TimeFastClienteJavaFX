/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.pojo;

/**
 *
 * @author sebas
 */
public class HistorialUnidad {
    private Integer idUnidad;
    private String marca;
    private String modelo;
    private String motivo;

    public HistorialUnidad(Integer idUnidad, String marca, String modelo, String motivo) {
        this.idUnidad = idUnidad;
        this.marca = marca;
        this.modelo = modelo;
        this.motivo = motivo;
    }

    public HistorialUnidad() {
    }
    

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    
}
