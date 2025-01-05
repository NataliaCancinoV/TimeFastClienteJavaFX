/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.ColaboradorDAO;
import clientetimefastjavafx.observador.NotificadorOperaciones;
import clientetimefastjavafx.pojo.Colaborador;
import clientetimefastjavafx.pojo.Mensaje;
import clientetimefastjavafx.utilidades.Utilidades;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author natal
 */
public class FXMLColaboradoresController implements Initializable, NotificadorOperaciones {

    @FXML
    private TableColumn<?, ?> tc_nopersonal;
    @FXML
    private TableColumn<?, ?> tc_nombre;
    @FXML
    private TableColumn<?, ?> tc_apellidopaterno;
    @FXML
    private TableColumn<?, ?> tc_apellidomaterno;
    @FXML
    private TableColumn<?, ?> tc_curp;
    @FXML
    private TableColumn<?, ?> tc_correo;
    @FXML
    private TableColumn<?, ?> tc_rol;
    @FXML
    private TableView<Colaborador> table_colaboradores;

    private ObservableList<Colaborador> colaboradores;
    @FXML
    private Button colaboradoresViewBtn;
    @FXML
    private Button unidadesViewBtn;
    @FXML
    private Button clientesViewBtn;
    @FXML
    private Button enviosViewBtn;
    @FXML
    private Button paquetesViewBtn;
    @FXML
    private TextField tfBuscarColaborador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarInformacionTabla();
    }

    private void configurarTabla() {
        tc_nopersonal.setCellValueFactory(new PropertyValueFactory("noPersonal"));//NOMBRE DEL ATRIBUTO DEL POJO CON EL QUE SE DEFINIO QUE SE VA A LLENAR
        tc_nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tc_apellidopaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        tc_apellidomaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        tc_curp.setCellValueFactory(new PropertyValueFactory("curp"));
        tc_correo.setCellValueFactory(new PropertyValueFactory("correo"));
        tc_rol.setCellValueFactory(new PropertyValueFactory("nombreColaborador"));
    }

    private void cargarInformacionTabla() {
        //LAS TABLAS NO SOPORTAN LIST SI NO UN OBSERVABLE LIST-> PORQUE USA UN PATRON DE DISÑEO OBSERVER
        colaboradores = FXCollections.observableArrayList();
        List<Colaborador> listaWS = ColaboradorDAO.obtenerColaboradores();
        if (listaWS != null) {
            colaboradores.addAll(listaWS);
            table_colaboradores.setItems(colaboradores);
        } else {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede cargar la información de los colaboradores, intentelo más tarde", Alert.AlertType.ERROR);

        }

    }

    @FXML
    private void cargarVistaUnidades(ActionEvent event) {
        try {
            Stage stageActual = (Stage) paquetesViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLUnidades.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cargarVistaClientes(ActionEvent event) {
        try {
            Stage stageActual = (Stage) paquetesViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLClientes.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cargarVsitaEnvios(ActionEvent event) {
        try {
            Stage stageActual = (Stage) paquetesViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLEnvios.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cargarVistaPaquetes(ActionEvent event) {
        try {
            Stage stageActual = (Stage) paquetesViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPaquetes.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void buscarColaborador(MouseEvent event) {
        String parametro = tfBuscarColaborador.getText();
        System.out.println("CONTENIDO PARAMETRO: " + parametro);
        if (parametro.trim().equals("") || parametro.trim().equals(" ")) {
            Utilidades.mostrarAlerta("Error Busqueda", "Campos de busqueda vacio", Alert.AlertType.INFORMATION);
        } else {
            try {
                String parametroCodicado = URLEncoder.encode(parametro, "UTF-8");
                colaboradores = FXCollections.observableArrayList();
                List<Colaborador> listaWS = ColaboradorDAO.buscarColaborador(parametroCodicado);
                if (listaWS != null) {
                    colaboradores.addAll(listaWS);
                    table_colaboradores.setItems(colaboradores);
                }
            } catch (Exception e) {
                System.out.println("Error cliente buscarColaborador: " + e.toString());
            }
        }

    }

    @FXML
    private void cargarFormularioColaborador(ActionEvent event) {
        irFormulario(this, null);
    }

    @Override
    public void notificarOperacion(String tipoOperacioin, String nombre) {
        System.out.println("Tipo operacion " + tipoOperacioin);
        System.out.println("Nombe del colaborador :" + nombre);
        cargarInformacionTabla();
    }

    public void irFormulario(NotificadorOperaciones observador, Colaborador colaborador) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLColaboradoresRegistrar.fxml"));
            Parent root = loader.load();

            FXMLColaboradoresRegistrarController controlador = loader.getController();
            controlador.inicializarValores(observador, colaborador);

            Stage escenario = new Stage();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Formulario Colaborador");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (Exception e) {
            Utilidades.mostrarAlerta("Error", "Lo sentimos por el momento no se puede mostrar formulario de colaboradores, intentelo más tarde", Alert.AlertType.ERROR);
        }
    }

    private void eliminarColaborador() {
        Integer idColaborador = table_colaboradores.getSelectionModel().getSelectedItem().getIdColaborador();

        System.out.println("ID COLABORADOR: " + idColaborador);
        Mensaje respuesta = ColaboradorDAO.eliminarColaborador(idColaborador);
        if (!respuesta.isError()) {
            Utilidades.mostrarAlerta("Eliminacion Exitosa", "El colaborador ha sido eliminado correctamente", Alert.AlertType.INFORMATION);
        } else {
            Utilidades.mostrarAlerta("Error elminacion", "Error al elminiar colaborador " + respuesta.getMensaje(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void cargarFormularioColaboradorEditar(ActionEvent event) {
        Colaborador colaboradorEditar = table_colaboradores.getSelectionModel().getSelectedItem();
        if (colaboradorEditar != null) {
            irFormulario(this, colaboradorEditar);
        } else {
            Utilidades.mostrarAlerta("Error Edición", "Debe seleccionar un registro para editar ", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void btnEliminarColaborador(ActionEvent event) {
        Colaborador colaborador = null;
        colaborador = table_colaboradores.getSelectionModel().getSelectedItem();
        if (colaborador != null) {
            eliminarColaborador();
            notificarOperacion("Eliminar", "Colaborador");
        } else {
            Utilidades.mostrarAlerta("Error elminacion", "Error debe seleccionar un registro ", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void cargarVistaColaboradores(ActionEvent event) {
        try {
            Stage stageActual = (Stage) paquetesViewBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLColaboradores.fxml"));
            Scene sceneUndiades = new Scene(root);
            stageActual.setScene(sceneUndiades);

        } catch (IOException ex) {
            Logger.getLogger(FXMLColaboradoresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnRefresh(MouseEvent event) {
        cargarInformacionTabla();
    }

    @FXML
    private void btnCerrarSesion(MouseEvent event) {
        try {
            Stage escenarioInicioSesion = (Stage) enviosViewBtn.getScene().getWindow();
            
            Parent inicioSesion = FXMLLoader.load(getClass().getResource("FXMLInicioSesion.fxml")); //Acceder al controlador para acceder a los datos
            
            Scene escenaInicioSesion = new Scene(inicioSesion);
            escenarioInicioSesion.setScene(escenaInicioSesion);
            escenarioInicioSesion.setTitle("Pantalla Inicio Sesion");
            escenarioInicioSesion.show();
            
        } catch (IOException ex) {
            Utilidades.mostrarAlerta("Error", "Lo sentimos, por el momento no podemos cerrar sesion", Alert.AlertType.ERROR);
        }
    }

}
