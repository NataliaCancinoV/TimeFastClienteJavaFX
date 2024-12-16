/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo.DAO;

import clientetimefastjavafx.modelo.ConexionWS;
import clientetimefastjavafx.pojo.Colaborador;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.pojo.RespuestaHTTP;
import clientetimefastjavafx.pojo.Unidad;
import clientetimefastjavafx.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebas
 */
public class UnidadDAO {

    public static List<Colaborador> obtenerConductores() {
        List<Colaborador> conductores = null;
        String urlWS = Constantes.URLWS + "colaborador/obtener-conductores";
        RespuestaHTTP respuestaWS = ConexionWS.peticionGET(urlWS);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Colaborador>>() {
            }.getType();
            conductores = gson.fromJson(respuestaWS.getContenido(), tipoLista);
        }
        return conductores;
    }

    public static Mensaje agregarUnidad(Unidad unidad) {
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS + "unidad/registrar-unidad";
        System.out.println("URL " + urlWS);
        Gson gson = new Gson();
        String parametros = gson.toJson(unidad);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPOSTJSON(urlWS, parametros);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
            System.out.println("Respeusta agregar Unidad: " + respuestaWS.getContenido());
        }
        return respuesta;
    }

    public static Mensaje editarUnidad(Unidad unidad) {
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS + "unidad/editar-unidad";
        Gson gson = new Gson();
        String parametro = gson.toJson(unidad);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPUTJSON(urlWS, parametro);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            System.out.println("Respuesta WS editar unidad: " + respuestaWS.getContenido());
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al registrar Colaboraodr");
        }
        return respuesta;
    }

    public static Mensaje eliminarUnidad(Integer idUnidad, String motivo) {
        Mensaje respuesta = new Mensaje();
        Gson gson = new Gson();
        String urlWS = Constantes.URLWS + "unidad/eliminar-unidad";
        String parametros = String.format("idUnidad=%s&motivo=%s", idUnidad, motivo);        
        RespuestaHTTP respuestaPeticion = ConexionWS.peticionPOST(urlWS, parametros);        
        if(respuestaPeticion.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            String jsonResultado = respuestaPeticion.getContenido();
            System.out.println("Repuesta de Json: "+jsonResultado);
           //SE REQUIERE EL JSON Y LA CLASE PARA TOMAR DE MOLDE
           //SE LE PASA LA CADENA DE TIPO JASON Y LA CLASE
           respuesta= gson.fromJson(respuestaPeticion.getContenido(), Mensaje.class);
        }else{
            respuesta.setError(true);
           respuesta.setMensaje("Lo sentimos" +respuestaPeticion.getContenido());            
        }
        return respuesta;
    }

    public static List<Unidad> obtenerUndiades() {
        List<Unidad> unidades = null;
        String urlWS = Constantes.URLWS + "unidad/obtener-unidades";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Unidad>>() {
            }.getType();
            unidades = gson.fromJson(respuesta.getContenido(), tipoLista);
        }
        return unidades;
    }
}
