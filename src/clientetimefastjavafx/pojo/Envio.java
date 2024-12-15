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
public class Envio {
    private Integer idEnvio;
    private Integer idCliente;
    private String calle;
    private Integer numero;
    private String colonia;
    private String cp;
    private String ciudad;
    private String estado;
    private String destino;
    private Integer noGuia;
    private Float costo;
    private String estatus;
    private String motivo;
    private Integer idConductor;
    private String nombreCliente;
    private Integer cantidadPaquetes;
    private String conductorAsignado;

    public Envio(Integer idEnvio, Integer idCliente, String calle, Integer numero, String colonia, String cp, String ciudad, String estado, String destino, Integer noGuia, Float costo, String estatus, String motivo, Integer idConductor, String nombreCliente, Integer cantidadPaquetes, String conductorAsignado) {
        this.idEnvio = idEnvio;
        this.idCliente = idCliente;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.cp = cp;
        this.ciudad = ciudad;
        this.estado = estado;
        this.destino = destino;
        this.noGuia = noGuia;
        this.costo = costo;
        this.estatus = estatus;
        this.motivo = motivo;
        this.idConductor = idConductor;
        this.nombreCliente = nombreCliente;
        this.cantidadPaquetes = cantidadPaquetes;
        this.conductorAsignado = conductorAsignado;
    }



    public Envio() {
    }

    public String getConductorAsignado() {
        return conductorAsignado;
    }

    public void setConductorAsignado(String conductorAsignado) {
        this.conductorAsignado = conductorAsignado;
    }
    
    

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Integer getCantidadPaquetes() {
        return cantidadPaquetes;
    }

    public void setCantidadPaquetes(Integer cantidadPaquetes) {
        this.cantidadPaquetes = cantidadPaquetes;
    }

    
    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getNoGuia() {
        return noGuia;
    }

    public void setNoGuia(Integer noGuia) {
        this.noGuia = noGuia;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }



    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Integer idConductor) {
        this.idConductor = idConductor;
    }
    
    
}
