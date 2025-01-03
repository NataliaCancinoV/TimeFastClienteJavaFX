/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetimefastjavafx;

import clientetimefastjavafx.modelo.DAO.UnidadDAO;
import clientetimefastjavafx.pojo.HistorialUnidad;
import clientetimefastjavafx.utilidades.Utilidades;
import java.net.URL;
import java.net.URLEncoder;
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
public class FXMLHistorialEliminacionUnidadesController implements Initializable {

    @FXML
    private TableView<HistorialUnidad> tableEliminacionUnidades;
    @FXML
    private TableColumn<?, ?> colMarca;
    @FXML
    private TableColumn<?, ?> colModelo;
    @FXML
    private TableColumn<?, ?> colMotivo;
    @FXML
    private TextField tfBuscarUnidad;

    private ObservableList<HistorialUnidad> unidadesEliminada = FXCollections.observableArrayList();

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
        colMarca.setCellValueFactory(new PropertyValueFactory("marca"));//NOMBRE DEL ATRIBUTO DEL POJO CON EL QUE SE DEFINIO QUE SE VA A LLENAR
        colModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        colMotivo.setCellValueFactory(new PropertyValueFactory("motivo"));
    }

    private void cargarDatosTabla() {
        List<HistorialUnidad> listaWS = UnidadDAO.obtenerHistorialEliminacion();
        if (listaWS != null) {
            unidadesEliminada.setAll(listaWS);
            tableEliminacionUnidades.setItems(unidadesEliminada);
        }
    }

    private void cargarDatosBusqueda(String marca) {
        try {
            String marcaCodificada = URLEncoder.encode(marca, "UTF-8");
            List<HistorialUnidad> listaWS = UnidadDAO.obstenerHistorialMarca(marca);
            if (listaWS != null) {
                unidadesEliminada.setAll(listaWS);
                tableEliminacionUnidades.setItems(unidadesEliminada);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener datos de busqueda "+e.toString());
        }
    }

    @FXML
    private void buscarUnidadEliminada(MouseEvent event) {
        if (campoBuscarNoVacio()) {
              String marca = tfBuscarUnidad.getText();
              cargarDatosBusqueda(marca);
        }
    }

    public boolean campoBuscarNoVacio() {
        boolean valido = true;
        if (tfBuscarUnidad.getText().equals("") && tfBuscarUnidad.getText().trim().equals("")) {
            Utilidades.mostrarAlerta("Error Busqueda", "Campo de busquead vacio", Alert.AlertType.ERROR);
            valido = false;
        }
        return valido;
    }
}
