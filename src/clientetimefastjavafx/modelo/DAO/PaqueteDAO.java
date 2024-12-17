/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo.DAO;

import clientetimefastjavafx.modelo.ConexionWS;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.pojo.Paquete;
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
public class PaqueteDAO {

    public static List<Paquete> obtenerPaquetes() {
        List<Paquete> paquetes = null;
        String urlWS = Constantes.URLWS + "paquete/obtener-paquetes";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoLista = new TypeToken<List<Paquete>>() {
                }.getType();
                paquetes = gson.fromJson(respuesta.getContenido(), tipoLista);

            } catch (Exception e) {
                System.out.println("Error al obtener lista paquetes: " + e.toString());
            }

        }
        return paquetes;
    }

    public static Mensaje agregarPaquete(Paquete paquete) {
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS + "paquete/registrar-paquete";
        Gson gson = new Gson();
        String parametros = gson.toJson(paquete);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPOSTJSON(urlWS, parametros);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
            System.out.println("RESPUESTA WS AGREGAR Paquete: " + respuestaWS.getContenido());
        }
        return respuesta;
    }
    
        public static Mensaje editarPaquete(Paquete paquete) {
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS + "paquete/actualizar-paquete";
        Gson gson = new Gson();
        String parametro = gson.toJson(paquete);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPUTJSON(urlWS, parametro);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            System.out.println("Respuesta WS editar paquete: " + respuestaWS.getContenido());
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al editar paquete");
        }
        return respuesta;
    }

    public static Mensaje eliminarPaquete(Integer idPaquete) {
        Mensaje respuesta = new Mensaje();
        Gson gson = new Gson();
        String urlWS = Constantes.URLWS + "paquete/eliminar-paquete/" + idPaquete;
        System.out.println("URL WS " + urlWS);
        RespuestaHTTP respuestaWS = ConexionWS.peticionDELETE(urlWS);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            System.out.println("Respuesta WS eliminar paquete: " + respuestaWS.getContenido());
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al eliminar paquete");
        }
        return respuesta;
    }
    
    public static List<Paquete> obtenerPaqueteEnvio(Integer noGuia){
        List<Paquete> paquetes = null;
        String uwlWS = Constantes.URLWS+"paquete/obtener-paquete-envio/"+noGuia;
        RespuestaHTTP respuestaWS = ConexionWS.peticionGET(uwlWS);
        if(respuestaWS.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Paquete>>(){}.getType();
            paquetes=gson.fromJson(respuestaWS.getContenido(), tipoLista);
        }
        return paquetes;
    }

}
