/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo;


import clientetimefastjavafx.pojo.RespuestaHTTP;
import clientetimefastjavafx.utilidades.Constantes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Hp
 */
public class ConexionWS {
    

    public static RespuestaHTTP peticionGET(String url){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();//PERMITE HACER
            //LA CONEXION HTTP POR MEDIO DE UNA URL
            //1.-SE CONFIGURA EL MÉTODO DE PETICÓN HTTP            
            conexionHttp.setRequestMethod("GET");//SE ESTABLECE EL TIPO DE PETICIÓN
            int codigoRespuesta = conexionHttp.getResponseCode();//PERMITE HACER LA PETICIÓN Y RETORNA CODIGO HTTP
            //LANZA LA CONEXIÓN Y RETORNA REPSUESTA HTTP -> CONTENIDO Y CODIGO DE RESPUESTA
            respuesta.setCodigoRespuesta(codigoRespuesta);//SE LE PASA EL CODIGO DE RESPUESTA A LA RESPUESTA
            System.out.println("Codigo WS: "+codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){//SI EL CODIGO DE RESPUESTA ES 200 ES QUE TRAE CONTENIDO
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));//SETEA EL COTENIDO TRAIDO
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {//ERROR AL CREAR URL, ALGO QUE AFECTA LA ESTRUCTURA DE LA URL
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){//POR SI NO PUEDE LEER LA PETICIÓN, POR CORRUPCIÓN DE DATOS ETC.
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
    
    public static RespuestaHTTP peticionPOST(String url, String parametros){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("POST");
            //SE AGREGA LA PROPIPEDA DE CONTENT TYPE PARA DECIRLE EL FORMATO DEL BODY
            conexionHttp.setRequestProperty("Content-Type", 
                    "application/x-www-form-urlencoded");
            //HACE AL CUERPO DE LA PETICIÓN EDITABLE, DADO QUE POR DEFAULT ES SOLO LECTURA ->
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());//CONVIERTE PARMAMETROS ARREGLO DE BYTES
            os.flush();//HACE QUE ENVIE PARAMMETROS
            os.close();//CIERRA LA CONEXION 
            int codigoRespuesta = conexionHttp.getResponseCode();//HACEMOS LA PETCIÓN
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){//SI EL CODIGO ES 200
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));//GUARDAMOS LA RESPUESTA
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
    
    public static RespuestaHTTP peticionPUT(String url, String parametros){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("PUT");
            conexionHttp.setRequestProperty("Content-Type", 
                    "application/x-www-form-urlencoded");
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
    
    public static RespuestaHTTP peticionDELETE(String url, String parametros){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("DELETE");
            conexionHttp.setRequestProperty("Content-Type", 
                    "application/x-www-form-urlencoded");
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        }  
        return respuesta;
    }
    
    public static RespuestaHTTP peticionPOSTJSON(String url, String parametros){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("POST");
            //SE AGREGA LA PROPIPEDA DE CONTENT TYPE PARA DECIRLE EL FORMATO DEL BODY
            conexionHttp.setRequestProperty("Content-Type", 
                    "application/json");
            //HACE AL CUERPO DE LA PETICIÓN EDITABLE, DADO QUE POR DEFAULT ES SOLO LECTURA ->
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());//CONVIERTE PARMAMETROS ARREGLO DE BYTES
            os.flush();//HACE QUE ENVIE PARAMMETROS
            os.close();//CIERRA LA CONEXION 
            int codigoRespuesta = conexionHttp.getResponseCode();//HACEMOS LA PETCIÓN
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){//SI EL CODIGO ES 200
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));//GUARDAMOS LA RESPUESTA
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
        public static RespuestaHTTP peticionPUTJSON(String url, String parametros){
        RespuestaHTTP respuesta = new RespuestaHTTP();
        try {
            URL urlDestino = new URL(url);
            HttpURLConnection conexionHttp = (HttpURLConnection) urlDestino.openConnection();
            conexionHttp.setRequestMethod("PUT");
            conexionHttp.setRequestProperty("Content-Type", 
                    "application/json");
            conexionHttp.setDoOutput(true);
            OutputStream os = conexionHttp.getOutputStream();
            os.write(parametros.getBytes());
            os.flush();
            os.close();
            int codigoRespuesta = conexionHttp.getResponseCode();
            respuesta.setCodigoRespuesta(codigoRespuesta);
            if(codigoRespuesta == HttpURLConnection.HTTP_OK){
                respuesta.setContenido(obtenerContenidoWS(conexionHttp.getInputStream()));
            }else{
                respuesta.setContenido("Código de respuesta HTTP: "+codigoRespuesta);
            }
        } catch (MalformedURLException e) {
            respuesta.setCodigoRespuesta(Constantes.ERROR_URL);
            respuesta.setContenido("Error el la dirección de conexión.");
        } catch (IOException io){
            respuesta.setCodigoRespuesta(Constantes.ERROR_PETICION);
            respuesta.setContenido("Error: no se pudo realizar la solicitud correspondiente.");
        } 
        return respuesta;
    }
    
    private static String obtenerContenidoWS(InputStream inputWS) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(inputWS));
        String inputLine;
        StringBuffer respuestaEntrada = new StringBuffer();
        while( (inputLine = in.readLine()) != null){
            respuestaEntrada.append(inputLine);
        }
        in.close();
        return respuestaEntrada.toString();
    }
}
