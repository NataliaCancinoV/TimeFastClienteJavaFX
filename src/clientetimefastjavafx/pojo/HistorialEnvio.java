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
public class HistorialEnvio {
    private Integer idCambio;
    private Integer idEnvio;
    private String estatus;
    private String fechaCambio;
    private Integer idColaborador;
    private Integer noGuia;
    private String nombreColaborador;
    private String motivo;

    public HistorialEnvio(Integer idCambio, Integer idEnvio, String estatus, String fechaCambio, Integer idColaborador, Integer noGuia, String nombreColaborador, String motivo) {
        this.idCambio = idCambio;
        this.idEnvio = idEnvio;
        this.estatus = estatus;
        this.fechaCambio = fechaCambio;
        this.idColaborador = idColaborador;
        this.noGuia = noGuia;
        this.nombreColaborador = nombreColaborador;
        this.motivo = motivo;
    }



    public HistorialEnvio() {
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getNoGuia() {
        return noGuia;
    }

    public void setNoGuia(Integer noGuia) {
        this.noGuia = noGuia;
    }

    public String getNombreColaborador() {
        return nombreColaborador;
    }

    public void setNombreColaborador(String nombreColaborador) {
        this.nombreColaborador = nombreColaborador;
    }

    
    public Integer getIdCambio() {
        return idCambio;
    }

    public void setIdCambio(Integer idCambio) {
        this.idCambio = idCambio;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(String fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }
    
    
}
