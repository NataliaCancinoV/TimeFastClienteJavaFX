/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.ColaboradorDAO;
import clientetimefastjavafx.modelo.DAO.RolDAO;
import clientetimefastjavafx.observador.NotificadorOperaciones;
import clientetimefastjavafx.pojo.Colaborador;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.pojo.Rol;
import clientetimefastjavafx.utilidades.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLColaboradoresRegistrarController implements Initializable {

    private NotificadorOperaciones observador;
    private Colaborador colaboradorEdicion;
    private boolean modoEdicion = false;
    private ObservableList<Rol> rolesSistema = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Rol> comboRoles;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPatenor;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCurp;
    @FXML
    private TextField tfNoPersonal;
    @FXML
    private TextField tfCorreo;
    @FXML
    private PasswordField tfContrasena;
    @FXML
    private TextField tfNoLicencia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfNoLicencia.setDisable(true);
        cargarTiposUsuarios();
        comboRoles.setValue(rolesSistema.get(0));  // Establecer un valor predeterminado
        comboRoles.valueProperty().addListener(new ChangeListener<Rol>() {
        @Override
        public void changed(ObservableValue<? extends Rol> observable, Rol oldValue, Rol newValue) {
 
            if (newValue != null) {
                
                if (newValue.getNombre().equals("Conductor")) {
                    tfNoLicencia.setDisable(false);  
                } else {
                    tfNoLicencia.setDisable(true); 
                }
            }
        }
    });
    }

    public void inicializarValores(NotificadorOperaciones observador, Colaborador colaboradorEdicion) {
        this.observador = observador;
        this.colaboradorEdicion = colaboradorEdicion;
        if (colaboradorEdicion != null) {
            modoEdicion = true;
            cargarDatosEdicion();
        }
    }   

    private void cargarDatosEdicion() {
       tfNombre.setText(colaboradorEdicion.getNombre());
       tfApellidoPatenor.setText(colaboradorEdicion.getApellidoPaterno());
       tfApellidoMaterno.setText(colaboradorEdicion.getApellidoMaterno());
       tfCorreo.setText(colaboradorEdicion.getCorreo());
       tfNoPersonal.setText(colaboradorEdicion.getNoPersonal());
       tfContrasena.setText(colaboradorEdicion.getContrasena());
       tfCurp.setText(colaboradorEdicion.getCurp());
       tfNoLicencia.setText(colaboradorEdicion.getNoLicencia());
       int posicion = buscarIDRol(colaboradorEdicion.getIdRol()-1);
       comboRoles.getSelectionModel().select(posicion);       
    }
    public int buscarIDRol(int idRol){
        int rol=0;
        for(int i=0;i<rolesSistema.size();i++){
            if(rolesSistema.get(i).getIdRol()==idRol){
               rol = rolesSistema.get(i).getIdRol();
            }
        }
        return rol;
    }

    private void cargarTiposUsuarios() {
        List<Rol> roles = RolDAO.obtenerRoles();
        if (roles == null || roles.isEmpty()) {
            Utilidades.mostrarAlerta("Error", "No se encontraron roles disponibles", Alert.AlertType.ERROR);
            return;
        }
        System.out.println("Roles cargados:");
        for (Rol rol : roles) {
            System.out.println("Rol: " + rol.getNombre());
        }
        rolesSistema.setAll(roles);
        comboRoles.setItems(rolesSistema);
    }

    private void guardarDatosColaborador(Colaborador colabroador) {
        Mensaje mensaje = ColaboradorDAO.registrarColaborador(colabroador);
        System.out.println("MOSTRADNO EL MENSAJE: " + mensaje.getMensaje());
        if (!mensaje.isError()) {
            Utilidades.mostrarAlerta("Registro Exitoso", "El colaborador ha sido registrado correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacion("Nuevo registro", colabroador.getNombre());
        } else {
            Utilidades.mostrarAlerta("Error registro", mensaje.getMensaje(), Alert.AlertType.ERROR);

        }
    }

    private void cerrarVentana() {
        Stage escenarioPrincipal = (Stage) comboRoles.getScene().getWindow();
        escenarioPrincipal.close();
    }
    
    private void editarDatosColaborador(Colaborador colaborador){
        Mensaje mensaje = ColaboradorDAO.editarColaborador(colaborador);
        if(!mensaje.isError()){
            Utilidades.mostrarAlerta("Edición Exitosa", "El colaborador ha sido editador correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            observador.notificarOperacion("Edicion Registros", colaborador.getNombre());            
        }else{
            Utilidades.mostrarAlerta("Error edicion", mensaje.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void guardarColaborador(ActionEvent event) {
        // Crear el objeto colaborador
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPatenor.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String curp = tfCurp.getText();
        String noPersonal = tfNoPersonal.getText();
        String correo = tfCorreo.getText();
        String contrasena = tfContrasena.getText();
        String noLicencia = tfNoLicencia.getText();
        String nombreRol = comboRoles.getSelectionModel().getSelectedItem().getNombre();
        Integer idRol = comboRoles.getSelectionModel().getSelectedItem().getIdRol();
        //APLICAR VALIDACIONES
        //Colaborador colaboradorRegistrar = new Colaborador(0, "Seasbtian", "Cordoba", "Castizo", "Sebastian@gmail.com", "123", "123", "COS031227HVZRSBA1", 1, "", "123", "Ejemplo");
        Colaborador colaboradorRegistrar = new Colaborador(0, nombre, apellidoPaterno, apellidoMaterno, correo, noPersonal, contrasena, curp, idRol, "", noLicencia, nombreRol);
         if(!modoEdicion){
            guardarDatosColaborador(colaboradorRegistrar);
          }else{
              Integer idColaborador = this.colaboradorEdicion.getIdColaborador();
              Colaborador  colaboradorEditar = new Colaborador(idColaborador, nombre, apellidoPaterno, apellidoMaterno,correo, noPersonal, contrasena, curp, idRol, "", noLicencia, nombreRol);        
              editarDatosColaborador(colaboradorEditar);
          } 
        System.out.println("ES MODO EDICION "+modoEdicion);
    }

}
