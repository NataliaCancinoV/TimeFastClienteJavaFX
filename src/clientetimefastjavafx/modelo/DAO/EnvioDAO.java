/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo.DAO;

import clientetimefastjavafx.modelo.ConexionWS;
import clientetimefastjavafx.pojo.Cliente;
import clientetimefastjavafx.pojo.Envio;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.pojo.RespuestaHTTP;
import clientetimefastjavafx.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.List;

/**
 *
 * @author sebas
 */
public class EnvioDAO {

    public static Mensaje agregarEnvio(Envio envio) {
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS + "envio/registrar-envio";
        Gson gson = new Gson();
        String parametros = gson.toJson(envio);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPOSTJSON(urlWS, parametros);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
            System.out.println("RESPUESTA WS AGREGAR ENVIO: " + respuestaWS.getContenido());
        }
        return respuesta;
    }
    
        public static Mensaje editarEnvio(Envio envio) {
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS + "envio/editar-envio";
        Gson gson = new Gson();
        String parametro = gson.toJson(envio);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPUTJSON(urlWS, parametro);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            System.out.println("Respuesta WS editar envio: " + respuestaWS.getContenido());
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al editar envio");
        }
        return respuesta;
    }
     
    public static List<Cliente> obtenerClientes() {
        List<Cliente> clientes = null;
        String urlWS = Constantes.URLWS + "cliente/obtener-clientes";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Type tipoLista = new TypeToken<List<Cliente>>() {
            }.getType();
            Gson gson = new Gson();
            clientes = gson.fromJson(respuesta.getContenido(), tipoLista);
        }
        return clientes;
    }   



    public static List<Envio> obtenerEnvios() {
        List<Envio> envios = null;
        String urlWS = Constantes.URLWS + "envio/obtener-envios";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Type tipoLista = new TypeToken<List<Envio>>() {
            }.getType();
            Gson gson = new Gson();
            envios = gson.fromJson(respuesta.getContenido(), tipoLista);
        }
        return envios;
    }
}
