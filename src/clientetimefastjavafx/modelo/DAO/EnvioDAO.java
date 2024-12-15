/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo.DAO;

import clientetimefastjavafx.modelo.ConexionWS;
import clientetimefastjavafx.pojo.Envio;
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
    public static List<Envio> obtenerEnvios(){
        List<Envio> envios=null;
        String urlWS = Constantes.URLWS+"envio/obtener-envios";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Type tipoLista = new TypeToken<List<Envio>>(){}.getType();
            Gson gson = new Gson();
            envios = gson.fromJson(respuesta.getContenido(), tipoLista);
        }
        return envios;
    }
}
