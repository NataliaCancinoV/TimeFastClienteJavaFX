/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo.DAO;

import clientetimefastjavafx.modelo.ConexionWS;
import clientetimefastjavafx.pojo.RespuestaHTTP;
import clientetimefastjavafx.pojo.Rol;
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
public class RolDAO {
    public static List<Rol> obtenerRoles(){
        List<Rol> roles = null;
        String URLWS=Constantes.URLWS+"rol/obtener-roles";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(URLWS);
        if(respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Rol>>(){}.getType();
            roles = gson.fromJson(respuesta.getContenido(), tipoLista);
        }else{
            System.out.println("Error al obtener los roles del sistema "+respuesta.getContenido());
        }
        return roles;
    }
}
