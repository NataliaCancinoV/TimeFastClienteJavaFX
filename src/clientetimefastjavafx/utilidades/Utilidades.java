
package clientetimefastjavafx.utilidades;

import javafx.scene.control.Alert;

/**
 *
 * @author Hp
 */
public class Utilidades {
    
    
    public static void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo){
        Alert alertaBienvenida = new Alert(tipo);//TIPO DE ALERTA DE ERROR
        alertaBienvenida.setTitle(titulo);
        alertaBienvenida.setHeaderText(null);//PUEDE LLEVAR UN TEXTO O DEJARLO NULO, CAMBIA EL DISEÑO
        alertaBienvenida.setContentText(contenido);
        alertaBienvenida.show();
    }
}
