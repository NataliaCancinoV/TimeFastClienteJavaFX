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

    public static List<Colaborador> obtenerColaboradores() {
        List<Colaborador> colaboradores = null;
        String urlWS = Constantes.URLWS + "colaborador/obtener-colaboradores";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {//LE VA A DECIR A JSON DE QUE TIPO ES LA LISTA, SOLO ES CON LISTAS
                //TYPETOKEN JSON -> TYPE ES DE JAVA.LANG
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>() {
                }.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }

    public static Mensaje registrarColaborador(Colaborador colaborador) {
        Mensaje respuesta = new Mensaje();

        String urlWS = Constantes.URLWS + "colaborador/registrar-colaborador";
        Gson gson = new Gson();
        String parametro = gson.toJson(colaborador);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPOSTJSON(urlWS, parametro);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            System.out.println("Respuesta WS editar Colaborador: " + respuestaWS.getContenido());
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al editar Colaboraodr");
        }
        return respuesta;
    }

    public static Mensaje editarColaborador(Colaborador colaborador) {
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS + "colaborador/editar-colaborador";
        System.out.println("CURP COLABORADOR: "+colaborador.getCurp());
        Gson gson = new Gson();
        String parametro = gson.toJson(colaborador);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPUTJSON(urlWS, parametro);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            System.out.println("Respuesta WS registrar Colaborador: " + respuestaWS.getContenido());
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al registrar Colaboraodr");
        }
        return respuesta;
    }

    public static Mensaje eliminarColaborador(Integer idColaborador) {
        Mensaje respuesta = new Mensaje();
        Gson gson = new Gson();
        String urlWS = Constantes.URLWS + "colaborador/eliminar-colaborador/"+idColaborador;
        System.out.println("URL WS "+urlWS);
        RespuestaHTTP respuestaWS = ConexionWS.peticionDELETE(urlWS);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            System.out.println("Respuesta WS eliminar Colaborador: " + respuestaWS.getContenido());
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al eliminar Colaboraodr");
        }
        return respuesta;
    }

    public static List<Colaborador> buscarColaborador(String parametro) {
        List<Colaborador> colaboradores = null;
        String urlWS = Constantes.URLWS + "colaborador/buscar-colaborador/" + parametro;
        System.out.println("URL WS SUBIR FOTO: " + urlWS);
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {//LE VA A DECIR A JSON DE QUE TIPO ES LA LISTA, SOLO ES CON LISTAS
                //TYPETOKEN JSON -> TYPE ES DE JAVA.LANG
                Type tipoListaColaborador = new TypeToken<List<Colaborador>>() {
                }.getType();
                colaboradores = gson.fromJson(respuesta.getContenido(), tipoListaColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    public static Mensaje subirFoto (Integer idColaborador, byte[] foto){
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS+"colaborador/subir-foto/"+idColaborador;
        RespuestaHTTP respuestaWS = ConexionWS.peticionPUTBinary(urlWS, foto);        
        if(respuestaWS.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            respuesta.setError(false);
            respuesta.setMensaje("Foto subida correctamente: "+respuestaWS.getContenido());
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Error al subir la foto");
        }
        return respuesta;
    }
    
    public static Colaborador obtenerFotoColaborador (Integer idColaborador){
        Colaborador colaborador= null;
        String urlWS = Constantes.URLWS+"colaborador/obtener-foto/"+idColaborador;
        RespuestaHTTP respuestaWS = ConexionWS.peticionGET(urlWS);
        if(respuestaWS.getCodigoRespuesta()==HttpURLConnection.HTTP_OK){
            Gson gson = new Gson();
            colaborador = gson.fromJson(respuestaWS.getContenido(), Colaborador.class);
            System.out.println("COLABORADOR FOTO : "+colaborador);
        }
        return colaborador;
    }
    

}
