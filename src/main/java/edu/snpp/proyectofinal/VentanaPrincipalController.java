/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.snpp.proyectofinal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author fredybogado
 */
public class VentanaPrincipalController implements Initializable {
        
    private static final Logger LOG = Logger.getLogger(VentanaPrincipalController.class.getName());
    
    @FXML
    public TabPane TabPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_proyectofinal_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
    }    
        
    private void cargarModulo(String direccionFXML, String tituloPestania) {
        boolean yaAbierto = false;
        for (Tab ta : this.TabPane.getTabs()) {
            if (ta.getText().equals(tituloPestania)) {
                this.TabPane.getSelectionModel().select(ta);
                yaAbierto = true;
                break;
            }
        }
        if (!yaAbierto) {
            try {
                FXMLLoader loader = new FXMLLoader();
                AnchorPane root = FXMLLoader.load(getClass().getResource(direccionFXML));
                Tab t = new Tab();
                t.setText(tituloPestania);
                t.setContent(root);
                this.TabPane.getTabs().add(t);
                this.TabPane.getSelectionModel().select(t);
            } catch (IOException ex) {
                LOG.log(Level.SEVERE, "Error al cargar modulo", ex);
                Alert errDlg = new Alert(Alert.AlertType.ERROR);
                errDlg.setTitle("Error al cargar módulo");
                errDlg.setHeaderText("Error al cargar módulo: '" + tituloPestania + "'. Archivo: '" + direccionFXML + "'.");
                errDlg.setContentText(ex.getMessage());
                errDlg.showAndWait();
            }
        }
    }
   
    @FXML
    private void OnActiononRegistrarEmpleado(ActionEvent event) {
        
        this.cargarModulo("/fxml/registroempleado/TablaEmpleadoFXML.fxml", "Tabla Empleado");
    }

}
