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
public class Unidad {
    private Integer idUnidad;
    private String marca;
    private String modelo;
    private String anio;
    private Integer vin;
    private String tipoUnidad;
    private Integer nii;
    private Integer idConductor;
    private String nombreConductor;

    public Unidad(Integer idUnidad, String marca, String modelo, String anio, Integer vin, String tipoUnidad, Integer nii, Integer idConductor, String nombreConductor) {
        this.idUnidad = idUnidad;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.vin = vin;
        this.tipoUnidad = tipoUnidad;
        this.nii = nii;
        this.idConductor = idConductor;
        this.nombreConductor = nombreConductor;
    }



    public Unidad() {
        
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

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Integer getVin() {
        return vin;
    }

    public void setVin(Integer vin) {
        this.vin = vin;
    }

    public String getTipoUnidad() {
        return tipoUnidad;
    }

    public void setTipoUnidad(String tipoUnidad) {
        this.tipoUnidad = tipoUnidad;
    }

    public Integer getNii() {
        return nii;
    }

    public void setNii(Integer nii) {
        this.nii = nii;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombreConductor() {
        return nombreConductor;
    }

    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }
    
    
}
