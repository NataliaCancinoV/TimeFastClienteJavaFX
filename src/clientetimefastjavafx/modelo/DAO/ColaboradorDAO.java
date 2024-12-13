/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo.DAO;

import clientetimefastjavafx.modelo.ConexionWS;
import clientetimefastjavafx.pojo.Colaborador;
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
public class ColaboradorDAO {
    public static List<Colaborador> obtenerColaboradores(){
        List<Colaborador> colaboradores = null;
        String urlWS= Constantes.URLWS+"colaborador/obtener-colaboradores";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if(respuesta.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
                        Gson gson = new Gson();
            try {//LE VA A DECIR A JSON DE QUE TIPO ES LA LISTA, SOLO ES CON LISTAS
                //TYPETOKEN JSON -> TYPE ES DE JAVA.LANG
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>(){}.getType();
                colaboradores= gson.fromJson(respuesta.getContenido(), tipoListaColaborador);                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
}
