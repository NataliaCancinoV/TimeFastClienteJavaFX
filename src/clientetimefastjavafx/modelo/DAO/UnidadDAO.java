/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo.DAO;

import clientetimefastjavafx.modelo.ConexionWS;
import clientetimefastjavafx.pojo.RespuestaHTTP;
import clientetimefastjavafx.pojo.Unidad;
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
public class UnidadDAO {
    public static List<Unidad> obtenerUndiades(){
        List<Unidad> unidades = null;
        String urlWS = Constantes.URLWS+"unidad/obtener-unidades";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Unidad>>(){}.getType();
            unidades = gson.fromJson(respuesta.getContenido(), tipoLista);
        }
        return unidades;
    }
}
