/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.LoginDAO;
import clientetimefastjavafx.pojo.Colaborador;
import clientetimefastjavafx.pojo.Login;
import clientetimefastjavafx.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField tfNoPersonal;
    @FXML
    private PasswordField pfContrasena;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) {
        String noPersonal = tfNoPersonal.getText().trim();
        String contrasena = pfContrasena.getText();
        if(validarCampos()){           
            Login respuesta = LoginDAO.iniciarSesion(noPersonal, contrasena);
            if((respuesta.getError()==false)){
                Colaborador colaborador = respuesta.getColaborador();
                Utilidades.mostrarAlerta("Inicio de Sesion Existoso", "Bienvenido de nuevo "+ colaborador.getNombre(), Alert.AlertType.INFORMATION);
                irPantallaInicio();
            }else{
                Utilidades.mostrarAlerta("Error al Iniciar Sesion", "Credenciales Incorrectas", Alert.AlertType.ERROR);
                System.out.println("Error inicio de sesion: "+respuesta.getMensaje());
            }
        }else{
            Utilidades.mostrarAlerta("Error", "Debes llenar todos los campos", Alert.AlertType.ERROR);
        }
    }
    
    public boolean validarCampos(){
        boolean esValido = false;
        if((!this.tfNoPersonal.getText().isEmpty()) && (!this.pfContrasena.getText().isEmpty())){
            esValido = true;
        }else{
            Utilidades.mostrarAlerta("Error", "Debes introducir todos los campos", Alert.AlertType.ERROR);
        }
        return esValido;
    }
    public void irPantallaInicio(){
        try {
            Stage pantallaPrincipal = (Stage) tfNoPersonal.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLColaboradoresRegistrar.fxml"));
            Scene scena = new Scene(root);
            pantallaPrincipal.setTitle("Pantalla Colaborador");
            pantallaPrincipal.setScene(scena);
            pantallaPrincipal.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLInicioSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

