/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.EnvioDAO;
import clientetimefastjavafx.pojo.HistorialEnvio;
import clientetimefastjavafx.utilidades.Utilidades;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sebas
 */
public class FXMLHistorialEstatusController implements Initializable {

    @FXML
    private TableView<HistorialEnvio> tableHistorialEstatus;
    @FXML
    private TableColumn<?, ?> colNoGuia;
    @FXML
    private TableColumn<?, ?> colEstastus;
    @FXML
    private TableColumn<?, ?> colConductor;
    @FXML
    private TableColumn<?, ?> colFecha;
    @FXML
    private TableColumn<?, ?> colMotivo;
    @FXML
    private TextField tfBuscarHistorial;

    private ObservableList<HistorialEnvio> envios = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabla();
        cargarDatosTabla();
    }

    public void configurarTabla() {
        colNoGuia.setCellValueFactory(new PropertyValueFactory("noGuia"));//NOMBRE DEL ATRIBUTO DEL POJO CON EL QUE SE DEFINIO QUE SE VA A LLENAR
        colConductor.setCellValueFactory(new PropertyValueFactory("nombreColaborador"));
        colEstastus.setCellValueFactory(new PropertyValueFactory("estatus"));
        colFecha.setCellValueFactory(new PropertyValueFactory("fechaCambio"));
        colMotivo.setCellValueFactory(new PropertyValueFactory("motivo"));
    }

    public void cargarDatosTabla() {
        List<HistorialEnvio> listaWS = EnvioDAO.obtenerHistorialEnviosEstatus();
        if (listaWS != null) {
            envios.setAll(listaWS);
            tableHistorialEstatus.setItems(envios);
        }
    }

    public void buscarEstatus(Integer noGuia) {
        List<HistorialEnvio> listaWS = EnvioDAO.obtenerHistorialEstatusNoGuia(noGuia);
        if (listaWS != null) {
            envios.setAll(listaWS);
            tableHistorialEstatus.setItems(envios);
        }
    }

    @FXML
    private void obtenerEstatusNoGuia(MouseEvent event) {
        if(campoBuscarNoVacio()){
            Integer noGuia = Integer.parseInt(tfBuscarHistorial.getText().trim());
            buscarEstatus(noGuia);
        }
    }

    public boolean campoBuscarNoVacio(){
        boolean valido=true;
        if(tfBuscarHistorial.getText().equals("") && tfBuscarHistorial.getText().trim().equals("")){
              Utilidades.mostrarAlerta("Error Busqueda", "Campo de busquead vacio", Alert.AlertType.ERROR); 
              valido = false;
        }
        return valido;
    }

}
