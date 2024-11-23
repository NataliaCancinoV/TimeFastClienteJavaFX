/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo.DAO;

import clientetimefastjavafx.modelo.ConexionWS;
import clientetimefastjavafx.pojo.Login;
import clientetimefastjavafx.pojo.RespuestaHTTP;
import clientetimefastjavafx.utilidades.Constantes;
import com.google.gson.Gson;
import java.net.HttpURLConnection;

/**
 *
 * @author sebas
 */
public class LoginDAO {
    public static Login iniciarSesion(String noPersonal, String contrasena){
        Login respuesta = new Login();
        String url = Constantes.URLWS + "login/login-colaborador";
        String parametros = String.format("noPersonal=%s&contrasena=%s", noPersonal, contrasena);
        RespuestaHTTP respuestaPeticion = ConexionWS.peticionPOST(url, parametros);
        if(respuestaPeticion.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            String jsonResultado = respuestaPeticion.getContenido();
            System.out.println("Repuesta de Json: "+jsonResultado);
            Gson gson= new Gson();
           //SE REQUIERE EL JSON Y LA CLASE PARA TOMAR DE MOLDE
           //SE LE PASA LA CADENA DE TIPO JASON Y LA CLASE
           respuesta= gson.fromJson(respuestaPeticion.getContenido(), Login.class);
        }else{
            respuesta.setError(true);
           respuesta.setMensaje("Lo sentimos" +respuestaPeticion.getContenido());            
        }
        return respuesta;
    }
}
