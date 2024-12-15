/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx.modelo.DAO;

import clientetimefastjavafx.modelo.ConexionWS;
import clientetimefastjavafx.pojo.Cliente;
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
public class ClienteDAO {

    public static List<Cliente> obtenerClientes() {
        List<Cliente> clientes = null;
        String urlWS = Constantes.URLWS + "cliente/obtener-clientes";
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            try {
                Type tipoLista = new TypeToken<List<Cliente>>() {
                }.getType();
                clientes = gson.fromJson(respuesta.getContenido(), tipoLista);

            } catch (Exception e) {
                System.out.println("Error al obtener lista clientes: " + e.toString());
            }

        }
        return clientes;
    }

    public static Mensaje agregarCliente(Cliente cliente) {
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS + "cliente/registrar-cliente";
        Gson gson = new Gson();
        String parametros = gson.toJson(cliente);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPOSTJSON(urlWS, parametros);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
            System.out.println("RESPUESTA WS AGREGAR CLIENTE: " + respuestaWS.getContenido());
        }
        return respuesta;
    }

    public static Mensaje editarCliente(Cliente cliente) {
        Mensaje respuesta = new Mensaje();
        String urlWS = Constantes.URLWS + "cliente/editar-cliente";
        Gson gson = new Gson();
        String parametro = gson.toJson(cliente);
        RespuestaHTTP respuestaWS = ConexionWS.peticionPUTJSON(urlWS, parametro);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            System.out.println("Respuesta WS editar cliente: " + respuestaWS.getContenido());
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al editar cliente");
        }
        return respuesta;
    }

    public static Mensaje eliminarCliente(Integer idCliente) {
        Mensaje respuesta = new Mensaje();
        Gson gson = new Gson();
        String urlWS = Constantes.URLWS + "cliente/eliminar-cliente/" + idCliente;
        System.out.println("URL WS " + urlWS);
        RespuestaHTTP respuestaWS = ConexionWS.peticionDELETE(urlWS);
        if (respuestaWS.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            System.out.println("Respuesta WS eliminar Cliente: " + respuestaWS.getContenido());
            respuesta = gson.fromJson(respuestaWS.getContenido(), Mensaje.class);
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Error al eliminar Cliente");
        }
        return respuesta;
    }

    public static List<Cliente> buscarCliente(String parametro) {
        List<Cliente> clientes = null;
        String urlWS = Constantes.URLWS + "cliente/buscar-cliente/" + parametro;
        RespuestaHTTP respuesta = ConexionWS.peticionGET(urlWS);
        if (respuesta.getCodigoRespuesta() == HttpURLConnection.HTTP_OK) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Cliente>>() {
            }.getType();
            clientes = gson.fromJson(respuesta.getContenido(), tipoLista);
        }
        return clientes;
    }
}
